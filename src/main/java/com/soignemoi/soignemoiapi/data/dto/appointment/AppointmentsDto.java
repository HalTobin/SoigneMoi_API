package com.soignemoi.soignemoiapi.data.dto.appointment;

import lombok.Data;

import java.util.List;

@Data
public class AppointmentsDto {

    AppointmentDto currentAppointment;
    List<AppointmentDto> futureAppointments;
    List<AppointmentDto> pastAppointments;

    public AppointmentsDto(
            AppointmentDto currentAppointment,
            List<AppointmentDto> futureAppointments,
            List<AppointmentDto> pastAppointments
    ) {
        this.currentAppointment = currentAppointment;
        this.futureAppointments = futureAppointments;
        this.pastAppointments = pastAppointments;
    }
}
