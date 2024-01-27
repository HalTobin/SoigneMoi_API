package com.soignemoi.soignemoiapi.data.dto.appointment;

import lombok.Data;

import java.util.Date;

@Data
public class AppointmentDataDto {

    private Date startDate;
    private Date endDate;
    private String reason;

    public AppointmentDataDto(Date startDate, Date endDate, String reason) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
    }
}
