package com.soignemoi.soignemoiapi.service;

import com.soignemoi.soignemoiapi.controller.VisitorController;
import com.soignemoi.soignemoiapi.data.model.Visitor;
import com.soignemoi.soignemoiapi.repository.VisitorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorService {

    private static final Logger logger = LoggerFactory.getLogger(VisitorController.class);

    @Autowired
    private VisitorRepository visitorRepository;

    public Visitor loadUserById(int id) {
        return visitorRepository.findById(id);
    }

    public Visitor loadUserByMail(String mail) throws Exception {
        Visitor visitor = visitorRepository.findByMail(mail);
        if (visitor == null) {
            throw new Exception("Error while login");
        }
        return visitor;
    }

}
