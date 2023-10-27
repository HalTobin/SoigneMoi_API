package com.soignemoi.soignemoiapi.data.model;

import com.soignemoi.soignemoiapi.data.values.Frequency;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class PrescriptionEntry {

    @Id
    private int id;

    private int prescriptionId;
    private int dosage;
    private Frequency frequency;
    private String note;

    public PrescriptionEntry() {}

    public PrescriptionEntry(
            int id,
            int prescriptionId,
            int dosage,
            Frequency frequency,
            String note
    ) {
        this.id = id;
        this.prescriptionId = prescriptionId;
        this.dosage = dosage;
        this.frequency = frequency;
        this.note = note;
    }

}
