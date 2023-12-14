package com.soignemoi.soignemoiapi.service;

import com.soignemoi.soignemoiapi.data.models.Prescription;
import com.soignemoi.soignemoiapi.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService {

    @Autowired
    PrescriptionRepository prescriptionRepository;

    public List<Prescription> getPrescriptionsByAppointmentsId(int appointmentId) {
        return prescriptionRepository.findByAppointmentId(appointmentId);
    }

}
