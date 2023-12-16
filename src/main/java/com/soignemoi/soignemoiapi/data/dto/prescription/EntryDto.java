package com.soignemoi.soignemoiapi.data.dto.prescription;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntryDto {

    private int prescriptionId;
    private int dosage;
    private int frequency;
    private String note;
    private int medicineId;

}
