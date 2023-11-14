package com.soignemoi.soignemoiapi.service;

import com.soignemoi.soignemoiapi.data.models.Visitor;
import com.soignemoi.soignemoiapi.repository.VisitorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class VisitorService {

    private static final Logger logger = LoggerFactory.getLogger(VisitorService.class);

    @Autowired
    private VisitorRepository visitorRepository;

    public void createVisitor(Visitor visitor) { visitorRepository.save(visitor); }

    public Visitor loadVisitorById(int id) {
        return visitorRepository.findById(id);
    }

    public Visitor loadVisitorByMail(String mail) throws UsernameNotFoundException {
        return visitorRepository
                .findByMail(mail)
                .orElseThrow(() -> new UsernameNotFoundException("Mail not found"));
    }

    public boolean isMailAvailable(String mail) { return !visitorRepository.existsByMail(mail); }

}
