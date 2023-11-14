package com.soignemoi.soignemoiapi.repository;

import com.soignemoi.soignemoiapi.data.models.Staff;
import com.soignemoi.soignemoiapi.data.values.StaffType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Integer> {

    Staff save(Staff staff);
    Optional<Staff> findByMail(String mail);
    boolean existsByStaffType(StaffType staffType);

}
