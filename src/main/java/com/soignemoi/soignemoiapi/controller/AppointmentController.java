package com.soignemoi.soignemoiapi.controller;

import com.soignemoi.soignemoiapi.data.dto.appointment.*;
import com.soignemoi.soignemoiapi.data.models.Appointment;
import com.soignemoi.soignemoiapi.data.models.Doctor;
import com.soignemoi.soignemoiapi.data.models.Specialty;
import com.soignemoi.soignemoiapi.data.models.Visitor;
import com.soignemoi.soignemoiapi.error.AppointmentAlreadyTaken;
import com.soignemoi.soignemoiapi.error.ValueNotFoundException;
import com.soignemoi.soignemoiapi.service.AppointmentService;
import com.soignemoi.soignemoiapi.service.DoctorService;
import com.soignemoi.soignemoiapi.service.SpecialtyService;
import com.soignemoi.soignemoiapi.service.VisitorService;
import com.soignemoi.soignemoiapi.service.DateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("appointment")
public class AppointmentController {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private VisitorService visitorService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private SpecialtyService specialtyService;
    @Autowired
    private DateService dateService;

    @GetMapping("authorized_date")
    public ResponseEntity<AvailableDateDto> getAvailableDate() {
        return new ResponseEntity<>(new AvailableDateDto(dateService.getAvailableTimestamp()), HttpStatus.OK);
    }

    @PostMapping("book")
    public ResponseEntity<BookAppointmentResponseDto> bookAnAppointment(
        @AuthenticationPrincipal UserDetails userDetails,
        @RequestBody NewAppointmentDto newAppointmentDto
    ) {
        try {
            Visitor visitor;
            Doctor doctor;
            Specialty specialty;
            try {
                visitor = visitorService.loadVisitorByMail(userDetails.getUsername());
                doctor = doctorService.loadDoctorByRegistrationNumber(newAppointmentDto.getDoctor());
                specialty = specialtyService.loadByTitle(newAppointmentDto.getSpecialty());
            } catch (ValueNotFoundException error) {
                return new ResponseEntity<>(
                    new BookAppointmentResponseDto(-1, BookAppointmentResponseDto.BookingStatus.ERROR),
                    HttpStatus.BAD_REQUEST
                );
            }
            try {
                Appointment newAppointment = new Appointment(
                        visitor,
                        doctor,
                        formatDate(newAppointmentDto.getStartDate()),
                        formatDate(newAppointmentDto.getEndDate()),
                        specialty,
                        newAppointmentDto.getReason()
                );
                Appointment savedAppointment = appointmentService.createAppointment(newAppointment);
                return new ResponseEntity<>(
                        new BookAppointmentResponseDto(
                                savedAppointment.getId(),
                                BookAppointmentResponseDto.BookingStatus.OK,
                                new AppointmentDataDto(newAppointment.getDateStart(), newAppointment.getDateEnd(), newAppointment.getReason())
                        ),
                        HttpStatus.OK
                );
            } catch (ParseException e) {
                return new ResponseEntity<>(new BookAppointmentResponseDto(-1, BookAppointmentResponseDto.BookingStatus.ERROR), HttpStatus.BAD_REQUEST);
            }
        } catch (AppointmentAlreadyTaken exception) {
            AppointmentDataDto existingAppointmentDto = new AppointmentDataDto(
                exception.getAppointment().getDateStart(),
                exception.getAppointment().getDateEnd(),
                exception.getAppointment().getReason()
            );
            return new ResponseEntity<>(
                    new BookAppointmentResponseDto(existingAppointmentDto),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @GetMapping("my_appointments")
    public ResponseEntity<AppointmentsDto> getMyAppointments(@AuthenticationPrincipal UserDetails userDetails) {
        try {
            Visitor visitor = visitorService.loadVisitorByMail(userDetails.getUsername());
            int visitorId = visitor.getId();
            Date currentDate = dateService.getCurrentDate();

            Appointment currentAppointment = appointmentService.getCurrentAppointment(currentDate, visitorId);
            AppointmentDto currentAppointmentDto = null;
            if (currentAppointment != null) currentAppointmentDto = mapToDto(currentAppointment);
            List<AppointmentDto> pastAppointmentsDto = appointmentService.getPastAppointments(currentDate, visitorId)
                    .stream().map(appointment -> mapToDto(appointment)).toList();
            List<AppointmentDto> futureAppointmentsDto = appointmentService.getFutureAppointments(currentDate, visitorId)
                    .stream().map(appointment -> mapToDto(appointment)).toList();

            AppointmentsDto appointmentsDto = new AppointmentsDto(currentAppointmentDto, futureAppointmentsDto, pastAppointmentsDto);
            return new ResponseEntity<>(appointmentsDto, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new AppointmentsDto(null, new ArrayList<>(), new ArrayList<>()), HttpStatus.BAD_REQUEST);
        }
    }

    private Date formatDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            System.out.println(dateString); // This will print the parsed Date object
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new ParseException(String.format("Could not parse %s", dateString), e.getErrorOffset());
        }
    }

    public static AppointmentDto mapToDto(Appointment appointment) {
        return new AppointmentDto(
                appointment.getId(),
                appointment.getVisitor().getId(),
                appointment.getDateStart(),
                appointment.getDateEnd(),
                appointment.getReason(),
                appointment.getSpecialty().getTitle(),
                appointment.getDoctor().getName()
        );
    }

}
