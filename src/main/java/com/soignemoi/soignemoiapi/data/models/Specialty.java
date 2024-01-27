package com.soignemoi.soignemoiapi.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    public Specialty() {}

    public Specialty(String title) { this.title = title; }

    public Specialty(int id, String title) {
        this.id = id;
        this.title = title;
    }

}
