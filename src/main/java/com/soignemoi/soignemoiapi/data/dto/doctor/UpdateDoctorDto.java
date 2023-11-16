package com.soignemoi.soignemoiapi.data.dto.doctor;

import lombok.Data;

@Data
public class UpdateDoctorDto {

    int id;
    String name;
    String surname;
    String registrationNumber;
    int specialtyId;
    String password;

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
}
