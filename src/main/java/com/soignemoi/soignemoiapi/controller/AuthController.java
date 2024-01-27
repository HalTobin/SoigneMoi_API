package com.soignemoi.soignemoiapi.controller;

import com.soignemoi.soignemoiapi.data.UserEntity;
import com.soignemoi.soignemoiapi.data.dto.auth.AuthResponseDto;
import com.soignemoi.soignemoiapi.data.dto.auth.LoginDto;
import com.soignemoi.soignemoiapi.data.dto.auth.RegisterDto;
import com.soignemoi.soignemoiapi.data.models.Visitor;
import com.soignemoi.soignemoiapi.security.JwtGenerator;
import com.soignemoi.soignemoiapi.security.SecurityConstant;
import com.soignemoi.soignemoiapi.service.user.UserService;
import com.soignemoi.soignemoiapi.service.VisitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private VisitorService visitorService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtGenerator jwtGenerator;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getMail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        UserEntity user = userService.findByIdentifier(loginDto.getMail());
        return new ResponseEntity<>(new AuthResponseDto(token, user.getRole().getRoleName(), SecurityConstant.JWT_EXPIRATION), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        System.out.println(registerDto.toString());
        if (visitorService.isMailAvailable(registerDto.getMail())) {
            Visitor newVisitor = new Visitor();
            newVisitor.setMail(registerDto.getMail());
            newVisitor.setName(registerDto.getName());
            newVisitor.setSurname(registerDto.getSurname());
            newVisitor.setPostCode(registerDto.getPostCode());
            newVisitor.setPassword(passwordEncoder.encode(registerDto.getPassword()));

            visitorService.createVisitor(newVisitor);
            return new ResponseEntity<>("Visitor registered success", HttpStatus.OK);
        } else return new ResponseEntity<>("Visitor registered fail", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/logout")
    public String logout() {
        // Implement logout logic
        return "redirect:/login?logout";
    }

}
