package com.soignemoi.soignemoiapi.controller;

import com.soignemoi.soignemoiapi.data.model.Visitor;
import com.soignemoi.soignemoiapi.service.VisitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/login")
    public String login(@RequestParam(value = "mail") String mail, @RequestParam(value = "password") String password) throws Exception {
        Visitor currentUser = visitorService.loadUserByMail(mail);
        System.out.println(currentUser.getMail());
        if (currentUser.getPassword().equals(password)) return "login";
        else return "error";
    }

    @GetMapping("/logout")
    public String logout() {
        // Implement logout logic
        return "redirect:/login?logout";
    }

}
