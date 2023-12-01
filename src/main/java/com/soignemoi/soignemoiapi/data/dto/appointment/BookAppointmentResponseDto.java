package com.soignemoi.soignemoiapi.data.dto.appointment;

import com.soignemoi.soignemoiapi.data.models.Appointment;
import lombok.Data;
import lombok.Getter;

@Data
public class BookAppointmentResponseDto {

    private int id;
    private BookingStatus status;
    private AppointmentDataDto appointmentData;

    public BookAppointmentResponseDto(
        int id,
        BookingStatus bookingStatus,
        AppointmentDataDto appointmentData
    ) {
        this.id = id;
        this.status = bookingStatus;
        this.appointmentData = appointmentData;
    }

    public BookAppointmentResponseDto(int id, BookingStatus status) {
        this.id = id;
        this.status = status;
    }

    public BookAppointmentResponseDto(AppointmentDataDto appointmentData) {
        this.id = -1;
        this.status = BookingStatus.NOT_AVAILABLE;
        this.appointmentData = appointmentData;
    }

    @Getter
    public enum BookingStatus {
        OK("Ok"),
        NOT_AVAILABLE("Not available"),
        ERROR("Error");

        private final String text;

        BookingStatus(String text) { this.text = text; }
    }
}
