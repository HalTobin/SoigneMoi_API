package com.soignemoi.soignemoiapi.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;
    private int doctorId;
    private Date dateStart;
    private Date dateEnd;
    private int specialtyId;
    private String reason;

    public Appointment() {}

    public Appointment(
            int id,
            int userId,
            int doctorId,
            Date dateStart,
            Date dateEnd,
            int specialtyId,
            String reason
    ) {
        this.id = id;
        this.userId = userId;
        this.doctorId = doctorId;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.specialtyId = specialtyId;
        this.reason = reason;
    }

}
