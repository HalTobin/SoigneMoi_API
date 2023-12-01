package com.soignemoi.soignemoiapi.service;

import com.soignemoi.soignemoiapi.data.models.DoctorVisit;
import com.soignemoi.soignemoiapi.error.ValueNotFoundException;
import com.soignemoi.soignemoiapi.repository.DoctorVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorVisitService {

    @Autowired
    DoctorVisitRepository doctorVisitRepository;

    public DoctorVisit create(DoctorVisit doctorVisit) {
        return doctorVisitRepository.save(doctorVisit);
    }

    public DoctorVisit getById(int id) throws ValueNotFoundException {
        return doctorVisitRepository.findById(id)
                .orElseThrow(() -> new ValueNotFoundException(String.format("No DoctorVisit found for this id: %13d", id)));
    }
    public List<DoctorVisit> getByDoctorId(int doctorId) {
        return doctorVisitRepository.findByDoctorId(doctorId);
    }

    public void updateDoneField(boolean done, int id) {
        doctorVisitRepository.setDoneForDoctorVisit(done, id);
    }

    public DoctorVisit getLatestByAppointmentId(int appointmentId) throws ValueNotFoundException {
        return doctorVisitRepository.findLatestByAppointmentId(appointmentId)
                .orElseThrow(() -> new ValueNotFoundException(String.format("No DoctorVisit found for this appointmentId: %13d", appointmentId)));
    }

    public boolean doesVisitExistByAppointmentIdAndDate(int appointmentId, Date date) { return doctorVisitRepository.existsByAppointmentIdAndDate(appointmentId, date); }

}
