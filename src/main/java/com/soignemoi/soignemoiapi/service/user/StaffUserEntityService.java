package com.soignemoi.soignemoiapi.service.user;

import com.soignemoi.soignemoiapi.data.UserEntity;
import com.soignemoi.soignemoiapi.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StaffUserEntityService implements IUserEntityService {
    @Autowired
    StaffService staffService;

    @Override
    public UserEntity findByIdentifier(String identifier) {
        try {
            return staffService.loadStaffByMail(identifier);
        } catch (UsernameNotFoundException e) { return null; }
    }
}
