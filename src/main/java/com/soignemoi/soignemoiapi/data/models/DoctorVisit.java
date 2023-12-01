package com.soignemoi.soignemoiapi.data.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class DoctorVisit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "visitorId")
    private Visitor visitor;
    @ManyToOne
    @JoinColumn(name = "doctorId")
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name = "appointmentId")
    private Appointment appointment;
    private Date date;
    private boolean done;

    public DoctorVisit() {}

    public DoctorVisit(
            int id,
            Visitor visitor,
            Doctor doctor,
            Appointment appointment,
            Date date,
            boolean done
    ) {
        this.id = id;
        this.visitor = visitor;
        this.doctor = doctor;
        this.appointment = appointment;
        this.date = date;
        this.done = done;
    }

    public DoctorVisit(
            int id,
            Visitor visitor,
            Doctor doctor,
            Appointment appointment,
            Date date
    ) {
        this.id = id;
        this.visitor = visitor;
        this.doctor = doctor;
        this.appointment = appointment;
        this.date = date;
        this.done = false;
    }

}
