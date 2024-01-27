package com.soignemoi.soignemoiapi.data.dto.patient;

import lombok.Data;

@Data
public class PatientSecretaryDto {

    private int id;
    private String name;
    private String surname;
    private String reason;
    private boolean entry; // if false, then patient is leaving today

    public PatientSecretaryDto(
            int id,
            String name,
            String surname,
            String reason,
            boolean entry
    ) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.reason = reason;
        this.entry = entry;
    }

}
