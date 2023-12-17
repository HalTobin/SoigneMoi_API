package com.soignemoi.soignemoiapi.repository;

import com.soignemoi.soignemoiapi.data.models.Prescription;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {

        Prescription save(Prescription prescription);
        List<Prescription> findByAppointmentId(int appointmentId);
        Prescription findByid(int id);
        @Transactional
        @Modifying
        @Query("UPDATE Prescription prescription SET prescription.endDate = :endDate WHERE prescription.id = :id")
        int updateEndDateField(@Param("id") int id, @Param("endDate") Date endDate);

}
