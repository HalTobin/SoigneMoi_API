package com.soignemoi.soignemoiapi.components;

import com.soignemoi.soignemoiapi.data.models.Doctor;
import com.soignemoi.soignemoiapi.data.models.Specialty;
import com.soignemoi.soignemoiapi.data.models.Staff;
import com.soignemoi.soignemoiapi.data.values.StaffType;
import com.soignemoi.soignemoiapi.service.DoctorService;
import com.soignemoi.soignemoiapi.service.SpecialtyService;
import com.soignemoi.soignemoiapi.service.StaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitComponent implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(InitComponent.class);

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private StaffService staffService;
    @Autowired
    private SpecialtyService specialtyService;
    @Autowired
    private DoctorService doctorService;

    @Override
    public void afterPropertiesSet() {
        // Generate a default admin
        if (!staffService.doesAdminExist()) {
            Staff newAdmin = new Staff();
            newAdmin.setMail("admin@soignemoi.fr");
            newAdmin.setName("Maruki");
            newAdmin.setSurname("Takuto");
            newAdmin.setPassword(passwordEncoder.encode("mtwgHvpg06vCrEQWRrjNkHPYnvRJDD0X"));
            newAdmin.setStaffType(StaffType.ADMIN);
            staffService.create(newAdmin);
            System.out.println("Admin created!");
        }
        // Generate default specialties
        if (specialtyService.loadSpecialties().isEmpty()) {
            Specialty[] specialties = new Specialty[]{
                    new Specialty("Cardiologie"),
                    new Specialty("Endocrinologie"),
                    new Specialty("Immunologie"),
                    new Specialty("Rhumatologie"),
                    new Specialty("Pneumologie"),
                    new Specialty("Psychiatrie")
            };
            for (Specialty specialty: specialties) {
                specialtyService.create(specialty);
            }
        }
        // Generate default doctors
        if (!doctorService.doDoctorsExist()) {
            Doctor[] doctors = new Doctor[]{
                    new Doctor("John", "Doe", 1, "REG123", passwordEncoder.encode("john.doe")),
                    new Doctor("Alice", "Smith", 2, "REG456", passwordEncoder.encode("alice.smith")),
                    new Doctor("Bob", "Johnson", 3, "REG789", passwordEncoder.encode("bob.johnson")),
                    new Doctor("Eleanor", "Rigby", 4, "REG101", passwordEncoder.encode("eleanor.rigby")),
                    new Doctor("Charlie", "Brown", 5, "REG112", passwordEncoder.encode("charlie.brown")),
                    new Doctor("Mia", "Wallace", 6, "REG131", passwordEncoder.encode("mia.wallace"))
            };
            for (Doctor doctor: doctors) {
                doctorService.create(doctor);
            }
        }
        System.out.println("Default entries created!");
    }

}
