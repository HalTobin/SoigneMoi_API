package com.soignemoi.soignemoiapi.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Time;

@Data
@Entity
public class Appointment {

    @Id
    private int id;

    private int userId;
    private int doctorId;
    private Time time;

    public Appointment() {}

    public Appointment(
            int id,
            int userId,
            int doctorId,
            Time time
    ) {
        this.id = id;
        this.userId = userId;
        this.doctorId = doctorId;
        this.time = time;
    }

}
