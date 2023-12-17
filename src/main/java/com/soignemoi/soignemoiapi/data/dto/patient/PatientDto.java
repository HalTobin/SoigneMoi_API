package com.soignemoi.soignemoiapi.data.dto.patient;

import lombok.Data;

@Data
public class PatientDto {

    private int id;
    private String name;
    private String surname;
    private String reason;

    public PatientDto(
            int id,
            String name,
            String surname,
            String reason
    ) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.reason = reason;
    }

}
