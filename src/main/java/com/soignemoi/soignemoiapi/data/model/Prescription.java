package com.soignemoi.soignemoiapi.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Prescription {

    @Id
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
