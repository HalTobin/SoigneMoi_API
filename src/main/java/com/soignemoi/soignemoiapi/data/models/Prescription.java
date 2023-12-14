package com.soignemoi.soignemoiapi.data.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int appointmentId;

    private Date start;
    private Date end;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "prescription_id", referencedColumnName = "id")
    private List<PrescriptionEntry> prescriptionEntries;

    public Prescription() {}

    public Prescription(
            int id,
            int appointmentId,
            Date start,
            Date end,
            List<PrescriptionEntry> prescriptionEntries
    ) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.start = start;
        this.end = end;
        this.prescriptionEntries = prescriptionEntries;
    }

}
