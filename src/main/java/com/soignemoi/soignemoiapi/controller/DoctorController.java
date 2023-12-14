package com.soignemoi.soignemoiapi.controller;

import com.soignemoi.soignemoiapi.data.dto.patient.PatientDetailsDto;
import com.soignemoi.soignemoiapi.data.dto.patient.PatientDto;
import com.soignemoi.soignemoiapi.data.models.Appointment;
import com.soignemoi.soignemoiapi.data.models.Doctor;
import com.soignemoi.soignemoiapi.data.models.Visitor;
import com.soignemoi.soignemoiapi.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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

    @GetMapping("auth_check")
    public ResponseEntity<String> checkAuth(@AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

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

        PatientDetailsDto patient = new PatientDetailsDto(
            visitor.getId(),
            visitor.getName(),
            visitor.getSurname(),
            appointment.getReason(),
            appointment.getSpecialty(),
            appointment.getDateStart(),
            appointment.getDateEnd(),
            noteService.getNotesByAppointmentId(appointment.getId()),
            prescriptionService.getPrescriptionsByAppointmentsId(appointment.getId())
        );

        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

}
