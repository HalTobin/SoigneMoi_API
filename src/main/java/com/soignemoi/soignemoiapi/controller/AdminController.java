package com.soignemoi.soignemoiapi.controller;

import com.soignemoi.soignemoiapi.data.dto.doctor.DoctorsDto;
import com.soignemoi.soignemoiapi.data.dto.doctor.NewDoctorDto;
import com.soignemoi.soignemoiapi.data.dto.doctor.UpdateDoctorDto;
import com.soignemoi.soignemoiapi.data.models.Doctor;
import com.soignemoi.soignemoiapi.error.ValueNotFoundException;
import com.soignemoi.soignemoiapi.service.DoctorService;
import com.soignemoi.soignemoiapi.service.SpecialtyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private SpecialtyService specialtyService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/get_doctors")
    public ResponseEntity<DoctorsDto> getDoctors(@AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(new DoctorsDto(doctorService.getAllDoctors()), HttpStatus.OK);
    }

    @PostMapping("/update_doctor")
    public ResponseEntity<String> update(@RequestBody UpdateDoctorDto updateDoctorDto) throws ValueNotFoundException {
        Doctor updateDoctor = new Doctor(
                updateDoctorDto.getId(),
                updateDoctorDto.getName(),
                updateDoctorDto.getSurname(),
                specialtyService.loadById(updateDoctorDto.getSpecialtyId()),
                updateDoctorDto.getRegistrationNumber(),
                passwordEncoder.encode(updateDoctorDto.getPassword())
        );
        doctorService.create(updateDoctor);
        return new ResponseEntity<>("Doctor updated", HttpStatus.OK);
    }

    @PostMapping("/create_doctor")
    public ResponseEntity<String> create(@RequestBody NewDoctorDto newDoctorDto) throws ValueNotFoundException {
        if (doctorService.doctorExist(newDoctorDto.getRegistrationNumber())) {
            return new ResponseEntity<>("This doctor already exists!", HttpStatus.BAD_REQUEST);
        }
        Doctor newDoctor = new Doctor(
                newDoctorDto.getName(),
                newDoctorDto.getSurname(),
                specialtyService.loadById(newDoctorDto.getSpecialtyId()),
                newDoctorDto.getRegistrationNumber(),
                passwordEncoder.encode(newDoctorDto.getPassword())
        );
        doctorService.create(newDoctor);
        return new ResponseEntity<>("Doctor registered", HttpStatus.OK);
    }
}
