package com.soignemoi.soignemoiapi.service.user;

import com.soignemoi.soignemoiapi.data.UserEntity;
import com.soignemoi.soignemoiapi.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class VisitorUserEntityService implements IUserEntityService {

    @Autowired
    VisitorService visitorService;

    @Override
    public UserEntity findByIdentifier(String identifier) {
        try {
            return visitorService.loadVisitorByMail(identifier);
        } catch (UsernameNotFoundException e) { return null; }
    }
}
