package com.soignemoi.soignemoiapi.data.dto;

import lombok.Data;

@Data
public class BasicUserDto {

    private String name;
    private String role;

    public BasicUserDto() {}

    public BasicUserDto(String name, String role) {
        this.name = name;
        this.role = role;
    }
}
