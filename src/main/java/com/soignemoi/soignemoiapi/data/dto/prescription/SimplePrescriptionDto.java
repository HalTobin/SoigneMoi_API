package com.soignemoi.soignemoiapi.data.dto.prescription;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class SimplePrescriptionDto {

    private int id;
    private int appointmentId;
    private Date start;
    private Date end;
    private List<SimpleEntryDto> entries;

}
