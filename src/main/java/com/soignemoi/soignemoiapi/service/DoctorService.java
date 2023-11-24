package com.soignemoi.soignemoiapi.service;

import com.soignemoi.soignemoiapi.data.dto.doctor.DoctorDto;
import com.soignemoi.soignemoiapi.data.models.Doctor;
import com.soignemoi.soignemoiapi.error.ValueNotFoundException;
import com.soignemoi.soignemoiapi.repository.DoctorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private static final Logger logger = LoggerFactory.getLogger(DoctorService.class);

    @Autowired
    private DoctorRepository doctorRepository;

    public void create(Doctor newDoctor) { doctorRepository.save(newDoctor); }

    public boolean doctorExist(String registrationNumber) {
        return doctorRepository.existsDoctorByRegistrationNumber(registrationNumber);
    }
    public boolean doDoctorsExist() {
        return (!doctorRepository.findAll().isEmpty());
    }

    public Doctor loadDoctorByRegistrationNumber(String registrationNumber) throws UsernameNotFoundException {
        return doctorRepository
                .findByRegistrationNumber(registrationNumber)
                .orElseThrow(() -> new UsernameNotFoundException("No doctor found for this registration number."));
    }

    public List<Doctor> getAllDoctors() { return doctorRepository.findAll(); }
}
