package com.soignemoi.soignemoiapi.error;

public class AppointmentAlreadyTaken extends Exception {
    public AppointmentAlreadyTaken(String errorMessage) { super(errorMessage); }
}
