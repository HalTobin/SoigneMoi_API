package com.soignemoi.soignemoiapi.data.dto.prescription;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AddPrescriptionDto {

    private Integer id;
    private int appointmentId;
    private String start;
    private String end;
    private List<AddEntryDto> entries;

}
