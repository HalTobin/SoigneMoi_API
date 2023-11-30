package com.soignemoi.soignemoiapi.data.dto.appointment;

import lombok.Data;

import java.util.Date;

@Data
public class AppointmentDto {

    int id;
    int visitorId;
    Date startDate;
    Date endDate;
    String reason;
    String specialty;
    String doctor;

    public AppointmentDto(
            int id,
            int visitorId,
            Date startDate,
            Date endDate,
            String reason,
            String specialty,
            String doctor
    ) {
        this.id = id;
        this.visitorId = visitorId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.specialty = specialty;
        this.doctor = doctor;
    }
}
