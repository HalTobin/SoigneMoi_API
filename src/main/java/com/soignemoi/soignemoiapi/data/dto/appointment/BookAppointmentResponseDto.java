package com.soignemoi.soignemoiapi.data.dto.appointment;

import com.soignemoi.soignemoiapi.data.models.Appointment;
import lombok.Data;
import lombok.Getter;

@Data
public class BookAppointmentResponseDto {

    private int id;
    private BookingStatus status;
    private ExistingAppointmentDto existingAppointment;

    public BookAppointmentResponseDto(
        int id,
        BookingStatus bookingStatus
    ) {
        this.id = id;
        this.status = bookingStatus;
    }

    public BookAppointmentResponseDto(ExistingAppointmentDto existingAppointmentDto) {
        this.id = -1;
        this.status = BookingStatus.NOT_AVAILABLE;
        this.existingAppointment = existingAppointmentDto;
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
