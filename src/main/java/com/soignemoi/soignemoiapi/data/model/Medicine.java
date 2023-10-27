package com.soignemoi.soignemoiapi.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Medicine {

    @Id
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
}
