package com.soignemoi.soignemoiapi.controller;


import com.soignemoi.soignemoiapi.data.dto.NewDoctorVisitDto;
import com.soignemoi.soignemoiapi.data.dto.VisitorDto;
import com.soignemoi.soignemoiapi.data.dto.appointment.AppointmentDto;
import com.soignemoi.soignemoiapi.data.dto.appointment.AppointmentForVisitDto;
import com.soignemoi.soignemoiapi.data.dto.doctor.DoctorForVisitDto;
import com.soignemoi.soignemoiapi.data.models.Appointment;
import com.soignemoi.soignemoiapi.data.models.DoctorVisit;
import com.soignemoi.soignemoiapi.error.ValueNotFoundException;
import com.soignemoi.soignemoiapi.service.AppointmentService;
import com.soignemoi.soignemoiapi.service.DateService;
import com.soignemoi.soignemoiapi.service.DoctorVisitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.soignemoi.soignemoiapi.service.DateService.daysBetween;


@RestController
@RequestMapping("doctor_visit")
public class DoctorVisitController {

    private static final Logger logger = LoggerFactory.getLogger(DoctorVisitController.class);

    @Autowired
    private DoctorVisitService doctorVisitService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private DateService dateService;

    @PostMapping("/new")
    public ResponseEntity<List<AppointmentForVisitDto>> newDoctorVisit(
        @RequestBody NewDoctorVisitDto newDoctorVisitDto
    ) {
        Appointment appointment = appointmentService.getById(newDoctorVisitDto.getAppointmentId());
        DoctorVisit newDoctorVisit = new DoctorVisit(
            appointment.getVisitor(),
            appointment.getDoctor(),
            appointment,
            dateService.getCurrentDate()
        );
        doctorVisitService.create(newDoctorVisit);
        return getAppointmentOfTheDay();
    }

    @GetMapping("/today_appointments")
    public ResponseEntity<List<AppointmentForVisitDto>> getAppointmentOfTheDay() {
        Date currenDate = dateService.getCurrentDate();
        List<Appointment> appointments = appointmentService.getAppointmentsByDate();
        System.out.println(String.format("Appointments for today: %13d", appointments.size()));
        List<AppointmentForVisitDto> dto = appointments
                .stream()
                .map(appointment -> {
                    AppointmentDto appointmentDto = new AppointmentDto(
                          appointment.getId(),
                          appointment.getVisitor().getId(),
                          appointment.getDateStart(),
                          appointment.getDateEnd(),
                          appointment.getReason(),
                          appointment.getSpecialty().getTitle(),
                          appointment.getDoctor().getName()
                    );
                    DoctorForVisitDto doctorDto = new DoctorForVisitDto(
                        appointment.getDoctor().getId(),
                        appointment.getDoctor().getName(),
                        appointment.getDoctor().getRegistrationNumber(),
                        doctorVisitService.howMuchVisitForTodayByDoctorId(appointment.getDoctor().getId())
                    );
                    VisitorDto visitorDto = new VisitorDto(
                        appointment.getVisitor().getName(),
                        appointment.getVisitor().getSurname(),
                        appointment.getVisitor().getMail(),
                        appointment.getVisitor().getPostCode()
                    );
                    int lastVisit = -1;
                    try {
                        Date lastDate = doctorVisitService.getLatestByAppointmentId(appointment.getId()).getDate();
                        lastVisit = daysBetween(lastDate, currenDate);
                    } catch (ValueNotFoundException exception) {
                        System.out.println("No DoctorVisit found for this Appointment");
                    }
                    return new AppointmentForVisitDto(
                            appointmentDto,
                            lastVisit,
                            doctorDto,
                            visitorDto,
                            doctorVisitService.doesVisitExistByAppointmentIdAndDate(appointment.getId())
                    );
                }).toList();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
