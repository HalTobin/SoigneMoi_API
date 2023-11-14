package com.soignemoi.soignemoiapi.data.dto.doctor;

import com.soignemoi.soignemoiapi.data.models.Doctor;

public class UpdateDoctorDto {

    int id;
    String name;
    String surname;
    String registrationNumber;
    int specialtyId;
    String  password;

    public UpdateDoctorDto(
            int id,
            String name,
            String surname,
            String registrationNumber,
            int specialtyId,
            String password
    ) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.registrationNumber = registrationNumber;
        this.specialtyId = specialtyId;
        this.password = password;
    }

    public Doctor getAsDoctor() {
        Doctor doctor = new Doctor();
        doctor.setId(id);
        doctor.setName(name);
        doctor.setSurname(surname);
        doctor.setSpecialtyId(specialtyId);
        doctor.setRegistrationNumber(registrationNumber);
        doctor.setPassword(password);
        return doctor;
    }
}
