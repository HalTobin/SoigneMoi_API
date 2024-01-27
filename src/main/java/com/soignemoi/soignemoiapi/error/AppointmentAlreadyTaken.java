package com.soignemoi.soignemoiapi.error;

import com.soignemoi.soignemoiapi.data.models.Appointment;

public class AppointmentAlreadyTaken extends RuntimeException {

    private final Appointment appointment;
    public AppointmentAlreadyTaken(String errorMessage, Appointment appointment) {
        super(errorMessage);
        this.appointment = appointment;
    }

    public Appointment getAppointment() { return appointment; }
}
