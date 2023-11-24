package com.soignemoi.soignemoiapi.data.dto.appointment;

import lombok.Data;

@Data
public class NewAppointmentDto {

    private String startDate;
    private String endDate;
    private String reason;
    private String specialty;
    private String doctor;

    public NewAppointmentDto() {}

    public NewAppointmentDto(
            String startDate,
            String endDate,
            String reason,
            String specialty,
            String doctor
    ) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.specialty = specialty;
        this.doctor = doctor;
    }
}
