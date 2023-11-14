package com.soignemoi.soignemoiapi.service.user;

import com.soignemoi.soignemoiapi.data.UserEntity;
import com.soignemoi.soignemoiapi.data.models.Doctor;
import com.soignemoi.soignemoiapi.data.models.Staff;
import com.soignemoi.soignemoiapi.data.models.Visitor;
import com.soignemoi.soignemoiapi.service.DoctorService;
import com.soignemoi.soignemoiapi.service.StaffService;
import com.soignemoi.soignemoiapi.service.VisitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private VisitorUserEntityService visitorService;
    @Autowired
    private StaffUserEntityService staffService;
    @Autowired
    private DoctorUserEntityService doctorService;

    public UserEntity findByMail(String identifier) throws UsernameNotFoundException {
        // First look for visitors
        UserEntity user = findUserInService(identifier, visitorService, "No visitor found for that mail");
        // If no matching result, search for staffs
        if (user == null) {
            user = findUserInService(identifier, staffService, "No staff found for that mail");
        }
        // If still no matching result, search for doctors
        if (user == null) {
            user = findUserInService(identifier, doctorService, "No doctor found for that registration number");
        }

        if (user == null) {
            throw new UsernameNotFoundException("Identifier doesn't match with database's entry");
        }

        return user;
    }

    private UserEntity findUserInService(String identifier, IUserEntityService service, String errorMessage) throws UsernameNotFoundException {
        try {
            return service.findByIdentifier(identifier).getAsUserEntity();
        } catch (Exception e) {
            System.out.println(errorMessage);
        }
        return null;
    }

}
