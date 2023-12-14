package com.soignemoi.soignemoiapi.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int appointmentId;
    private int doctorId;
    private int userId;
    private String title;
    private String content;
    private Date date;

    public Note() {}

    public Note(
            int id,
            int appointmentId,
            int doctorId,
            int userId,
            String title,
            String content,
            Date date
    ) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.date = date;
    }

}
