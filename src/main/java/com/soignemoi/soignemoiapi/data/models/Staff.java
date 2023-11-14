package com.soignemoi.soignemoiapi.data.models;

import com.soignemoi.soignemoiapi.data.UserEntity;
import com.soignemoi.soignemoiapi.data.values.Role;
import com.soignemoi.soignemoiapi.data.values.StaffType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
public class Staff extends UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String mail;
    private String name;
    private String surname;
    private String password;
    private StaffType staffType;

    public Staff() {}

    public Staff(
            int id,
            String mail,
            String name,
            String surname,
            String password,
            StaffType staffType
    ) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.staffType = staffType;
    }

    @Override
    public Role getRole() {
        return switch (staffType) {
            case ADMIN -> Role.ADMIN;
            case SECRETARY -> Role.SECRETARY;
        };
    }
    @Override
    public String getUsername() { return mail; }
    @Override
    public String getPassword() { return password; }


}
