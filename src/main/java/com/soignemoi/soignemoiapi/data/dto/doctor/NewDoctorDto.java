package com.soignemoi.soignemoiapi.data.dto.doctor;

import com.soignemoi.soignemoiapi.data.models.Doctor;
import lombok.Data;

@Data
public class NewDoctorDto {

    private String name;
    private String surname;
    private String registrationNumber;
    private int specialtyId;
    private String password;

    public NewDoctorDto() {}

    public NewDoctorDto(String name, String surname, String registrationNumber, int specialtyId, String password) {
        this.name = name;
        this.surname = surname;
        this.registrationNumber = registrationNumber;
        this.specialtyId = specialtyId;
        this.password = password;
    }

    public Doctor getAsDoctor() {
        Doctor doctor = new Doctor();
        doctor.setName(name);
        doctor.setSurname(surname);
        doctor.setRegistrationNumber(registrationNumber);
        doctor.setPassword(password);
        doctor.setSpecialtyId(specialtyId);
        return doctor;
    }
}
