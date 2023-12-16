package com.soignemoi.soignemoiapi.repository;

import com.soignemoi.soignemoiapi.data.models.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {

        Prescription save(Prescription prescription);
        List<Prescription> findByAppointmentId(int appointmentId);

        Prescription findByid(int id);

}
