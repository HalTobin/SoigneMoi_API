package com.soignemoi.soignemoiapi.data.model;

import com.soignemoi.soignemoiapi.data.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import com.soignemoi.soignemoiapi.data.values.Role;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "visitor")
public class Visitor extends UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String surname;
    @Column(name = "mail")
    private String mail;
    private String password;
    private String postCode;

    public Visitor() {}

    public Visitor(
            int id,
            String name,
            String surname,
            String mail,
            String password,
            String postCode
    ) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.password = password;
        this.postCode = postCode;
    }

    @Override
    public Role getRole() { return Role.VISITOR; }

}
