package com.soignemoi.soignemoiapi.service;

import com.soignemoi.soignemoiapi.data.models.Staff;
import com.soignemoi.soignemoiapi.data.values.StaffType;
import com.soignemoi.soignemoiapi.repository.StaffRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StaffService {

    private static final Logger logger = LoggerFactory.getLogger(StaffService.class);

    @Autowired
    private StaffRepository staffRepository;

    public void create(Staff newStaff) { staffRepository.save(newStaff); }

    public boolean doesAdminExist() { return staffRepository.existsByStaffType(StaffType.ADMIN); }

    public boolean doReceptionExist() { return staffRepository.existsByStaffType(StaffType.SECRETARY); }

    public Staff loadStaffByMail(String mail) throws UsernameNotFoundException {
        return staffRepository
                .findByMail(mail)
                .orElseThrow(() -> new UsernameNotFoundException("Mail not found"));
    }
}