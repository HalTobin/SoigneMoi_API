package com.soignemoi.soignemoiapi.service;

import com.soignemoi.soignemoiapi.data.models.Appointment;
import com.soignemoi.soignemoiapi.error.AppointmentAlreadyTaken;
import com.soignemoi.soignemoiapi.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    public Appointment createAppointment(Appointment newAppointment) throws AppointmentAlreadyTaken {
        appointmentRepository.findAppointmentByDateAndVisitorId(newAppointment.getDateStart(), newAppointment.getDateEnd(), newAppointment.getVisitor().getId())
            .ifPresent(appointment -> {
                    throw new AppointmentAlreadyTaken(
                            "Visitor already has an appointment for these dates",
                            appointment
                    );
                });
        return appointmentRepository.save(newAppointment);
    }

    public List<Appointment> getAllByDoctorId(int doctorId) { return appointmentRepository.findAllByDoctorId(doctorId); }

    public List<Appointment> getAllByVisitorId(int visitorId) { return appointmentRepository.findAllByVisitorId(visitorId); }

    public boolean doesAppointmentExist(Date startDate, Date endDate, int visitorId) {
        return appointmentRepository.doesAppointmentExist(startDate, endDate, visitorId);
    }

    public Appointment findAppointmentByDatesAndVisitorId(Date startDate, Date endDate, int visitorId) {
        return appointmentRepository.findAppointmentByDateAndVisitorId(startDate, endDate, visitorId)
                .orElse(null);
    }

    public Appointment getCurrentAppointment(Date date, int visitorId) {
        return appointmentRepository
                .findCurrentAppointment(date, visitorId)
                .orElse(null);
    }

    public List<Appointment> getPastAppointments(Date date, int visitorId) {
        return appointmentRepository.findPastAppointments(date, visitorId);
    }

    public List<Appointment> getFutureAppointments(Date date, int visitorId) {
        return appointmentRepository.findFutureAppointments(date, visitorId);
    }

}