package com.soignemoi.soignemoiapi.data.dto.auth;

import lombok.Data;

@Data
public class LoginDto {

    String mail;
    String password;

    public LoginDto() {}

    public LoginDto(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

}
