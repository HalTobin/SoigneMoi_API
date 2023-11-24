package com.soignemoi.soignemoiapi.controller;

import com.soignemoi.soignemoiapi.data.dto.doctor.AdminPanelDto;
import com.soignemoi.soignemoiapi.data.dto.doctor.DoctorDto;
import com.soignemoi.soignemoiapi.service.DoctorService;
import com.soignemoi.soignemoiapi.service.SpecialtyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("common")
public class CommonController {

    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private SpecialtyService specialtyService;

    @GetMapping("/get_doctors")
    public ResponseEntity<AdminPanelDto> getDoctors() {
        AdminPanelDto doctorsDto = new AdminPanelDto(
                doctorService.getAllDoctors().stream().map(doctor ->
                        new DoctorDto(
                                doctor.getId(),
                                doctor.getName(),
                                doctor.getSurname(),
                                doctor.getRegistrationNumber(),
                                doctor.getSpecialty()
                        )
                ).toList(),
                specialtyService.loadSpecialties()
        );
        return new ResponseEntity<>(doctorsDto, HttpStatus.OK);
    }
}
