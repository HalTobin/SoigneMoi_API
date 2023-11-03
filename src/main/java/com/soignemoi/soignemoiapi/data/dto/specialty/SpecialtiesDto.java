package com.soignemoi.soignemoiapi.data.dto.specialty;

import lombok.Data;

import java.util.List;

@Data
public class SpecialtiesDto {

    private List<SpecialtyDto> specialties;

    public SpecialtiesDto() {}

    public SpecialtiesDto(List<SpecialtyDto> specialties) { this.specialties = specialties; }

}
