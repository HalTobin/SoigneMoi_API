package com.soignemoi.soignemoiapi.data.dto.appointment;

import lombok.Data;
import lombok.Getter;

@Data
public class BookAppointmentResponseDto {

    private int id;
    private BookingStatus status;

    public BookAppointmentResponseDto(
        int id,
        BookingStatus bookingStatus
    ) {
        this.id = id;
        this.status = bookingStatus;
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
