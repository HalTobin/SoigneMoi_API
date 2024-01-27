package com.soignemoi.soignemoiapi.data.dto;

import lombok.Data;

@Data
public class NewDoctorVisitDto {

    int appointmentId;

    public NewDoctorVisitDto() {}

    public NewDoctorVisitDto(int appointmentId) { this.appointmentId = appointmentId; }

}
