package com.soignemoi.soignemoiapi.data.dto.doctor;

import com.soignemoi.soignemoiapi.data.models.Specialty;
import lombok.Data;

@Data
public class DoctorDto {

    private int id;
    private String name;
    private String surname;
    private String registrationNumber;
    private Specialty specialty;

    public DoctorDto(
            int id,
            String name,
            String surname,
            String registrationNumber,
            Specialty specialty
    ) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.registrationNumber = registrationNumber;
        this.specialty = specialty;
    }
}
