package com.soignemoi.soignemoiapi.data.dto.specialty;

import lombok.Data;

@Data
public class SpecialtyDto {

    private String title;

    public SpecialtyDto() {}

    public SpecialtyDto(String title) { this.title = title; }

}
