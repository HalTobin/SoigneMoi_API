package com.soignemoi.soignemoiapi.data.models;

import com.soignemoi.soignemoiapi.data.UserEntity;
import com.soignemoi.soignemoiapi.data.values.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
public class Doctor extends UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String surname;
    //private int specialtyId;
    @ManyToOne
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;
    private String registrationNumber;
    private String password;

    public Doctor() {}

    public Doctor(
            String name,
            String surname,
            Specialty specialty,
            String registrationNumber,
            String password
    ) {
        this.name = name;
        this.surname = surname;
        this.specialty = specialty;
        this.registrationNumber = registrationNumber;
        this.password = password;
    }

    public Doctor(
            int id,
            String name,
            String surname,
            Specialty specialty,
            String registrationNumber,
            String password
    ) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.specialty = specialty;
        this.registrationNumber = registrationNumber;
        this.password = password;
    }

    @Override
    public String getUsername() { return registrationNumber; }
    @Override
    public String getPassword() { return password; }
    @Override
    public Role getRole() { return Role.DOCTOR; }
}
