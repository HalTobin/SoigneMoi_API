package com.soignemoi.soignemoiapi.data.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "visitorId")
    private Visitor visitor;
    @ManyToOne
    @JoinColumn(name = "doctorId")
    private Doctor doctor;
    private Date dateStart;
    private Date dateEnd;
    @ManyToOne
    @JoinColumn(name = "specialtyId")
    private Specialty specialty;
    private String reason;

    public Appointment() {}

    public Appointment(
            Visitor visitor,
            Doctor doctor,
            Date dateStart,
            Date dateEnd,
            Specialty specialty,
            String reason
    ) {
        this.visitor = visitor;
        this.doctor = doctor;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.specialty = specialty;
        this.reason = reason;
    }

    public Appointment(
            int id,
            Visitor visitor,
            Doctor doctor,
            Date dateStart,
            Date dateEnd,
            Specialty specialty,
            String reason
    ) {
        this.id = id;
        this.visitor = visitor;
        this.doctor = doctor;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.specialty = specialty;
        this.reason = reason;
    }

}
