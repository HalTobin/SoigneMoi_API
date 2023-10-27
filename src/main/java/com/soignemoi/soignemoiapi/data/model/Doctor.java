package com.soignemoi.soignemoiapi.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Doctor {

    @Id
    private int id;

    private String name;
    private String surname;
    private int specialtyId;
    private String registrationNumber;
    private String password;

    public Doctor() {}

    public Doctor(
            int id,
            String name,
            String surname,
            int specialtyId,
            String registrationNumber,
            String password
    ) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.specialtyId = specialtyId;
        this.registrationNumber = registrationNumber;
        this.password = password;
    }
}
