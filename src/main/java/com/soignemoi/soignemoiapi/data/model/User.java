package com.soignemoi.soignemoiapi.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    private int id;

    private String name;
    private String surname;
    private String mail;
    private String password;

    public User() {}

    public User(
            int id,
            String name,
            String surname,
            String mail,
            String password
    ) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.password = password;
    }

}
