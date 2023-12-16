package com.soignemoi.soignemoiapi.controller;

import com.soignemoi.soignemoiapi.data.dto.appointment.AppointmentDto;
import com.soignemoi.soignemoiapi.data.dto.doctor.DoctorDto;
import com.soignemoi.soignemoiapi.data.dto.note.AddNoteDto;
import com.soignemoi.soignemoiapi.data.dto.note.ResultNoteDto;
import com.soignemoi.soignemoiapi.data.dto.patient.PatientDetailsDto;
import com.soignemoi.soignemoiapi.data.dto.patient.PatientDto;
import com.soignemoi.soignemoiapi.data.dto.prescription.EntryDto;
import com.soignemoi.soignemoiapi.data.dto.prescription.PrescriptionDto;
import com.soignemoi.soignemoiapi.data.models.*;
import com.soignemoi.soignemoiapi.data.values.Frequency;
import com.soignemoi.soignemoiapi.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;


@RestController
@RequestMapping("doctor")
public class DoctorController {

    private static final Logger logger = LoggerFactory.getLogger(DoctorController.class);

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private VisitorService visitorService;
    @Autowired
    private DoctorVisitService doctorVisitService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private PrescriptionService prescriptionService;
    @Autowired
    private NoteService noteService;
    @Autowired
    private DateService dateService;
    @Autowired
    private MedicineService medicineService;

    @GetMapping("my_patients")
    public ResponseEntity<List<PatientDto>> getMyPatients(@AuthenticationPrincipal UserDetails userDetails) {
        Doctor doctor = doctorService.loadDoctorByRegistrationNumber(userDetails.getUsername());
        List<Visitor> visitors = doctorVisitService.getVisitForTodayByDoctorId(doctor.getId())
                .stream().map(doctorVisit -> doctorVisit.getVisitor()).toList();
        List<PatientDto> dto = visitors
                .stream()
                .map(visitor -> {
                    Appointment appointment = appointmentService.getCurrentAppointment(visitor.getId());
                    return new PatientDto(
                        visitor.getId(),
                        visitor.getName(),
                        visitor.getSurname(),
                        appointment.getReason()
                    );
                }).toList();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("patient_details")
    public ResponseEntity<PatientDetailsDto> getPatientDetails(@RequestParam int patientId) {
        Visitor visitor = visitorService.loadVisitorById(patientId);
        Appointment appointment = appointmentService.getCurrentAppointment(patientId);
        Doctor doctor = appointment.getDoctor();
        AppointmentDto appointmentDto = new AppointmentDto(
                appointment.getId(),
                appointment.getVisitor().getId(),
                appointment.getDateStart(),
                appointment.getDateEnd(),
                appointment.getReason(),
                appointment.getSpecialty().getTitle(),
                appointment.getDoctor().getRegistrationNumber()
        );
        DoctorDto doctorDto = new DoctorDto(
                doctor.getId(),
                doctor.getName(),
                doctor.getSurname(),
                doctor.getRegistrationNumber(),
                doctor.getSpecialty()
        );

        PatientDetailsDto patient = new PatientDetailsDto(
            visitor.getId(),
            visitor.getName(),
            visitor.getSurname(),
            appointmentDto,
            doctorDto,
            appointment.getSpecialty(),
            appointment.getDateStart(),
            appointment.getDateEnd(),
            noteService.getNotesByAppointmentId(appointment.getId()),
            prescriptionService.getPrescriptionsByAppointmentId(appointment.getId())
        );

        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @PostMapping("new_note")
    public ResponseEntity<ResultNoteDto> saveNote(@RequestBody AddNoteDto noteDto) {
        Note note = new Note(
                noteDto.getAppointmentId(),
                noteDto.getDoctorId(),
                noteDto.getUserId(),
                noteDto.getTitle(),
                noteDto.getContent(),
                dateService.getCurrentDate()
        );
        Note result = noteService.createNote(note);
        ResultNoteDto resultDto = new ResultNoteDto(
                result.getId(),
                result.getAppointmentId(),
                result.getDoctorId(),
                result.getUserId(),
                result.getTitle(),
                result.getContent(),
                result.getDate()
        );
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    @GetMapping("medicines")
    public ResponseEntity<List<Medicine>> getMedicines() {
        List<Medicine> medicines = medicineService.getAllMedicines();
        return new ResponseEntity<>(medicines, HttpStatus.OK);
    }

    @GetMapping("get_prescription")
    public ResponseEntity<PrescriptionDto> getPrescription(@RequestParam int prescriptionId) {
        Prescription prescription = prescriptionService.getPrescriptionById(prescriptionId);
        PrescriptionDto dto = new PrescriptionDto(
                prescription.getId(),
                prescription.getAppointmentId(),
                prescription.getStart(),
                prescription.getEnd(),
                prescription.getPrescriptionEntries().stream().map(entry ->
                        new EntryDto(
                                entry.getPrescriptionId(),
                                entry.getDosage(),
                                entry.getFrequency().getId(),
                                entry.getNote(),
                                entry.getMedicine().getId()
                        )
                        ).toList()
        );
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("save_prescription")
    public ResponseEntity<Boolean> savePrescription(@RequestBody PrescriptionDto newPrescriptionDto) {
        System.out.println(newPrescriptionDto.toString());
        if (newPrescriptionDto.getId() == null) {
            Prescription newPrescription = new Prescription(
                    newPrescriptionDto.getAppointmentId(),
                    newPrescriptionDto.getStart(),
                    newPrescriptionDto.getEnd(),
                    newPrescriptionDto.getEntries().stream().map(entry ->
                            new PrescriptionEntry(
                                    entry.getDosage(),
                                    Frequency.getFromId(entry.getFrequency()),
                                    entry.getNote(),
                                    medicineService.getById(entry.getMedicineId())
                            )
                            ).toList()
            );
            prescriptionService.create(newPrescription);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
