package com.soignemoi.soignemoiapi.controller;

import com.soignemoi.soignemoiapi.data.dto.VisitorDto;
import com.soignemoi.soignemoiapi.data.model.Visitor;
import com.soignemoi.soignemoiapi.security.JwtGenerator;
import com.soignemoi.soignemoiapi.service.VisitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("visitor")
public class VisitorController {

    private static final Logger logger = LoggerFactory.getLogger(VisitorController.class);

    @Autowired
    private VisitorService visitorService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/profile")
    public ResponseEntity<VisitorDto> getProfile(@AuthenticationPrincipal UserDetails userDetails) {
        try {
            Visitor visitor = visitorService.loadVisitorByMail(userDetails.getUsername());
            VisitorDto dto = new VisitorDto(
                    visitor.getName(),
                    visitor.getSurname(),
                    visitor.getMail(),
                    visitor.getPostCode()
            );
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
