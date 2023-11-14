package com.soignemoi.soignemoiapi.data.dto;

import lombok.Data;

@Data
public class UserDto {

    private String name;
    private String surname;
    private String mail;
    private String role;

    public UserDto() {}

    public UserDto(String name, String surname, String mail, String role) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.role = role;
    }
}
