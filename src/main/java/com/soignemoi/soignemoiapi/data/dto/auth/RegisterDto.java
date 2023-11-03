package com.soignemoi.soignemoiapi.data.dto.auth;

import lombok.Data;

@Data
public class RegisterDto {

    String mail;
    String name;
    String surname;
    String postCode;
    String password;

    public RegisterDto() {}

    public RegisterDto(String mail, String name, String surname, String postCode, String password) {
        this.mail = mail;
        this.name = name;
        this.surname = surname;
        this.postCode = postCode;
        this.password = password;
    }
}
