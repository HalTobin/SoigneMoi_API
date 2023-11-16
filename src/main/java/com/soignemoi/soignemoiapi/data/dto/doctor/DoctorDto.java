package com.soignemoi.soignemoiapi.data.dto.doctor;

import lombok.Data;

@Data
public class DoctorDto {

    private int id;
    private String name;
    private String surname;
    private String registrationNumber;
    private int specialtyId;
    private String specialtyName;

    public DoctorDto(
            int id,
            String name,
            String surname,
            String registrationNumber,
            int specialtyId,
            String specialtyName
    ) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.registrationNumber = registrationNumber;
        this.specialtyId = specialtyId;
        this.specialtyName = specialtyName;
    }
}
