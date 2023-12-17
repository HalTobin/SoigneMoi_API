package com.soignemoi.soignemoiapi.data.dto.prescription;

import com.soignemoi.soignemoiapi.data.models.Medicine;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntryDto {

    private int prescriptionId;
    private int dosage;
    private int frequency;
    private String note;
    private Medicine medicine;

}
