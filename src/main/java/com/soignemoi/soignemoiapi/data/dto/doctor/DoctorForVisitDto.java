package com.soignemoi.soignemoiapi.data.dto.doctor;

import lombok.Data;

@Data
public class DoctorForVisitDto {

    private int id;
    private String name;
    private String registrationNumber;
    private int visitToday;

    public DoctorForVisitDto(
            int id,
            String name,
            String registrationNumber,
            int visitToday
    ) {
        this.id = id;
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.visitToday = visitToday;
    }
}
