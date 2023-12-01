package com.soignemoi.soignemoiapi.data.dto.appointment;

import lombok.Data;

@Data
public class AvailableDateDto {

    private Long timestamp;

    public AvailableDateDto(Long timestamp) { this.timestamp = timestamp; }

}
