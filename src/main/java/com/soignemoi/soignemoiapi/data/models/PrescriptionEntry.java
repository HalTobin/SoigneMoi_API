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

    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;
    private int dosage;
    private Frequency frequency;
    private String note;
    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    public PrescriptionEntry() {}

    public PrescriptionEntry(
            int id,
            Prescription prescription,
            int dosage,
            Frequency frequency,
            String note,
            Medicine medicine
    ) {
        this.id = id;
        this.prescription = prescription;
        this.dosage = dosage;
        this.frequency = frequency;
        this.note = note;
        this.medicine = medicine;
    }

    public PrescriptionEntry(
            Prescription prescription,
            int dosage,
            Frequency frequency,
            String note,
            Medicine medicine
    ) {
        this.prescription = prescription;
        this.dosage = dosage;
        this.frequency = frequency;
        this.note = note;
        this.medicine = medicine;
    }

}
