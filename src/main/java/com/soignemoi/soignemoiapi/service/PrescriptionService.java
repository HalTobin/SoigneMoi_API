package com.soignemoi.soignemoiapi.service;

import com.soignemoi.soignemoiapi.data.models.Prescription;
import com.soignemoi.soignemoiapi.data.models.PrescriptionEntry;
import com.soignemoi.soignemoiapi.repository.PrescriptionEntryRepository;
import com.soignemoi.soignemoiapi.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PrescriptionService {

    @Autowired
    PrescriptionRepository prescriptionRepository;

    public List<Prescription> getPrescriptionsByAppointmentId(int appointmentId) {
        return prescriptionRepository.findByAppointmentId(appointmentId);
    }

    public Prescription getPrescriptionById(int id) {
        return prescriptionRepository.findByid(id);
    }

    public void create(Prescription newPrescription) {
        prescriptionRepository.save(newPrescription);
    }

    public void updateEndDate(int prescriptionId, Date newEndDate) {
        prescriptionRepository.updateEndDateField(prescriptionId, newEndDate);
    }

}
