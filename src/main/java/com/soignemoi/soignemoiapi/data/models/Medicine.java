package com.soignemoi.soignemoiapi.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    public Medicine() {}

    public Medicine(
            int id,
            String title
    ) {
        this.id = id;
        this.title = title;
    }

    public Medicine(String title) { this.title = title; }
}
