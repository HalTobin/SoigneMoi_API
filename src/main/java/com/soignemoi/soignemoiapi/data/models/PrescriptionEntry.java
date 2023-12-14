package com.soignemoi.soignemoiapi.data.models;

import com.soignemoi.soignemoiapi.data.values.Frequency;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class PrescriptionEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "prescription_id")
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
