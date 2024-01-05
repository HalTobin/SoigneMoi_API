package com.soignemoi.soignemoiapi.controller;

import com.soignemoi.soignemoiapi.data.dto.appointment.AppointmentDto;
import com.soignemoi.soignemoiapi.data.dto.doctor.DoctorDto;
import com.soignemoi.soignemoiapi.data.dto.patient.PatientSecretaryDetailsDto;
import com.soignemoi.soignemoiapi.data.dto.patient.PatientSecretaryDto;
import com.soignemoi.soignemoiapi.data.dto.prescription.EntryDto;
import com.soignemoi.soignemoiapi.data.dto.prescription.PrescriptionDto;
import com.soignemoi.soignemoiapi.data.dto.prescription.SimpleEntryDto;
import com.soignemoi.soignemoiapi.data.dto.prescription.SimplePrescriptionDto;
import com.soignemoi.soignemoiapi.data.models.Appointment;
import com.soignemoi.soignemoiapi.data.models.Doctor;
import com.soignemoi.soignemoiapi.data.models.Prescription;
import com.soignemoi.soignemoiapi.data.models.Visitor;
import com.soignemoi.soignemoiapi.service.AppointmentService;
import com.soignemoi.soignemoiapi.service.NoteService;
import com.soignemoi.soignemoiapi.service.PrescriptionService;
import com.soignemoi.soignemoiapi.service.VisitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("secretary")
public class SecretaryController {

    private static final Logger logger = LoggerFactory.getLogger(SecretaryController.class);

    @Autowired
    private VisitorService visitorService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private PrescriptionService prescriptionService;
    @Autowired
    private NoteService noteService;

    @GetMapping("today_patients")
    public ResponseEntity<List<PatientSecretaryDto>> getTodayPatients() {
        List<PatientSecretaryDto> todayPatient = new java.util.ArrayList<>(Collections.emptyList());
        List<Appointment> starting = appointmentService.getStartingAppointments();
        List<Appointment> ending = appointmentService.getEndingAppointments();

        todayPatient.addAll(
                starting.stream().map(appointment -> {
                    Visitor visitor = appointment.getVisitor();
                    return new PatientSecretaryDto(
                            visitor.getId(),
                            visitor.getName(),
                            visitor.getSurname(),
                            appointment.getReason(),
                            true
                    );
                }).toList()
        );

        todayPatient.addAll(
                ending.stream().map(appointment -> {
                    Visitor visitor = appointment.getVisitor();
                    return new PatientSecretaryDto(
                            visitor.getId(),
                            visitor.getName(),
                            visitor.getSurname(),
                            appointment.getReason(),
                            false
                    );
                }).toList()
        );

        return new ResponseEntity<>(todayPatient, HttpStatus.OK);
    }

    @GetMapping("patient_details")
    public ResponseEntity<PatientSecretaryDetailsDto> getPatientDetails(@RequestParam int patientId) {
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

        PatientSecretaryDetailsDto patient = new PatientSecretaryDetailsDto(
                visitor.getId(),
                visitor.getName(),
                visitor.getSurname(),
                visitor.getMail(),
                appointmentDto,
                doctorDto,
                appointment.getSpecialty(),
                appointment.getDateStart(),
                appointment.getDateEnd(),
                noteService.getNotesByAppointmentId(appointment.getId()),
                prescriptionService.getPrescriptionsByAppointmentId(appointment.getId()).stream().map(prescription ->
                        new PrescriptionDto(
                                prescription.getId(),
                                prescription.getAppointmentId(),
                                prescription.getStartDate(),
                                prescription.getEndDate(),
                                prescription.getPrescriptionEntries().stream().map(entry ->
                                        new EntryDto(
                                                entry.getPrescription().getId(),
                                                entry.getDosage(),
                                                entry.getFrequency().getId(),
                                                entry.getNote(),
                                                entry.getMedicine()
                                        )
                                ).toList()
                        )
                ).toList()
        );

        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

}
