package com.soignemoi.soignemoiapi.data.model;

import com.soignemoi.soignemoiapi.data.values.StaffType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String surname;
    private String password;
    private StaffType staffType;

    public Staff() {}

    public Staff(
            int id,
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
}
