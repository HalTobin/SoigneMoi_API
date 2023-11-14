package com.soignemoi.soignemoiapi.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date start;
    private Date end;

    public Prescription() {}

    public Prescription(
            int id,
            Date start,
            Date end
    ) {
        this.id = id;
        this.start = start;
        this.end = end;
    }

}
