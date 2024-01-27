package com.soignemoi.soignemoiapi.controller;

import com.soignemoi.soignemoiapi.data.dto.doctor.NewDoctorDto;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/create_doctor")
    public ResponseEntity<String> create(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody NewDoctorDto newDoctorDto) throws ValueNotFoundException {
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
