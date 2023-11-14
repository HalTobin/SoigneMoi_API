package com.soignemoi.soignemoiapi.repository;

import com.soignemoi.soignemoiapi.data.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    Doctor save(Doctor doctor);
    Doctor findById(int id);
    Optional<Doctor> findByRegistrationNumber(String registrationNumber);
    List<Doctor> findAll();
}
