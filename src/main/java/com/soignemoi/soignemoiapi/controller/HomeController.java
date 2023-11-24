package com.soignemoi.soignemoiapi.controller;

import com.soignemoi.soignemoiapi.data.UserEntity;
import com.soignemoi.soignemoiapi.data.dto.BasicUserDto;
import com.soignemoi.soignemoiapi.data.dto.specialty.SpecialtiesDto;
import com.soignemoi.soignemoiapi.data.dto.specialty.SpecialtyDto;
import com.soignemoi.soignemoiapi.data.models.Specialty;
import com.soignemoi.soignemoiapi.service.SpecialtyService;
import com.soignemoi.soignemoiapi.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("home")
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private SpecialtyService specialtyService;
    @Autowired
    private UserService userService;

    @GetMapping("/specialties")
    public ResponseEntity<SpecialtiesDto> getSpecialties() {
        List<Specialty> specialties = specialtyService.loadSpecialties();
        SpecialtiesDto dto = new SpecialtiesDto(
                specialties
                        .stream()
                        .map(specialty -> new SpecialtyDto(specialty.getTitle())).toList()
        );
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/current_user")
    public ResponseEntity<BasicUserDto> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        UserEntity user = userService.findByIdentifier(userDetails.getUsername());
        BasicUserDto dto = new BasicUserDto(user.getUsername(), user.getRole().getRoleName());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
