package com.soignemoi.soignemoiapi.controller;

import com.soignemoi.soignemoiapi.data.dto.appointment.AvailableDateDto;
import com.soignemoi.soignemoiapi.data.dto.appointment.BookAppointmentResponseDto;
import com.soignemoi.soignemoiapi.data.dto.appointment.ExistingAppointmentDto;
import com.soignemoi.soignemoiapi.data.dto.appointment.NewAppointmentDto;
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
import com.soignemoi.soignemoiapi.service.user.DateService;
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
import java.util.Date;

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
                        new BookAppointmentResponseDto(savedAppointment.getId(), BookAppointmentResponseDto.BookingStatus.OK),
                        HttpStatus.OK
                );
            } catch (ParseException e) {
                return new ResponseEntity<>(new BookAppointmentResponseDto(-1, BookAppointmentResponseDto.BookingStatus.ERROR), HttpStatus.BAD_REQUEST);
            }
        } catch (AppointmentAlreadyTaken exception) {
            ExistingAppointmentDto existingAppointmentDto = new ExistingAppointmentDto(
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

    private Date formatDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            System.out.println(dateString); // This will print the parsed Date object
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new ParseException(String.format("Could not parse %s", dateString), e.getErrorOffset());
        }
    }

}
