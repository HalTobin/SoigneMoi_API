package com.soignemoi.soignemoiapi.service.user;

import com.soignemoi.soignemoiapi.data.UserEntity;
import com.soignemoi.soignemoiapi.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DoctorUserEntityService implements IUserEntityService {
    @Autowired
    DoctorService doctorService;

    @Override
    public UserEntity findByIdentifier(String identifier) {
        try {
            return doctorService.loadDoctorByRegistrationNumber(identifier);
        } catch (UsernameNotFoundException e) { return null; }
    }
}
