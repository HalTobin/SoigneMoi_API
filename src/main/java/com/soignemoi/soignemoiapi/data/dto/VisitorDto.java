package com.soignemoi.soignemoiapi.data.dto;

import lombok.Data;

@Data
public class VisitorDto {

    private String name;
    private String surname;
    private String mail;
    private String postCode;

    public VisitorDto() {}

    public VisitorDto(String name, String surname, String mail, String postCode) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.postCode = postCode;
    }

}
