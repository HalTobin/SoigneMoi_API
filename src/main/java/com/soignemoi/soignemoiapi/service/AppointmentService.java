package com.soignemoi.soignemoiapi.service;

import com.soignemoi.soignemoiapi.data.models.Appointment;
import com.soignemoi.soignemoiapi.error.AppointmentAlreadyTaken;
import com.soignemoi.soignemoiapi.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    public Appointment createAppointment(Appointment newAppointment) throws AppointmentAlreadyTaken {
        boolean appointmentAvailable = !appointmentRepository.doesAppointmentExist(newAppointment.getDateStart(), newAppointment.getDateEnd(), newAppointment.getVisitor().getId());
        if (appointmentAvailable) return appointmentRepository.save(newAppointment);
        else throw new AppointmentAlreadyTaken("Visitor already has an appointment for these dates");
    }

    public List<Appointment> getAllByDoctorId(int doctorId) { return appointmentRepository.findAllByDoctorId(doctorId); }

    public List<Appointment> getAllByVisitorId(int visitorId) { return appointmentRepository.findAllByVisitorId(visitorId); }

    public boolean doesAppointmentExist(Date startDate, Date endDate, int visitorId) {
        return appointmentRepository.doesAppointmentExist(startDate, endDate, visitorId);
    }

}