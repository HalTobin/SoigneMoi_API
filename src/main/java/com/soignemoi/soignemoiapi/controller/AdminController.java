package com.soignemoi.soignemoiapi.controller;

import com.soignemoi.soignemoiapi.data.dto.doctor.DoctorsDto;
import com.soignemoi.soignemoiapi.data.dto.doctor.NewDoctorDto;
import com.soignemoi.soignemoiapi.data.dto.doctor.UpdateDoctorDto;
import com.soignemoi.soignemoiapi.service.DoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/get_doctors")
    public ResponseEntity<DoctorsDto> getDoctors() {
        return new ResponseEntity<>(new DoctorsDto(doctorService.getAllDoctors()), HttpStatus.OK);
    }

    @PostMapping("/update_doctor")
    public ResponseEntity<String> update(@RequestBody UpdateDoctorDto updateDoctorDto) {
        doctorService.create(updateDoctorDto.getAsDoctor());
        return new ResponseEntity<>("Doctor updated", HttpStatus.OK);
    }

    @PostMapping("/create_doctor")
    public ResponseEntity<String> create(@RequestBody NewDoctorDto newDoctorDto) {
        if (doctorService.doctorExist(newDoctorDto.getRegistrationNumber())) {
            return new ResponseEntity<>("This doctor already exists!", HttpStatus.BAD_REQUEST);
        }
        doctorService.create(newDoctorDto.getAsDoctor());
        return new ResponseEntity<>("Doctor registered", HttpStatus.OK);
    }
}
