package com.soignemoi.soignemoiapi.data.values;

import lombok.Getter;

@Getter
public enum Frequency {
    DAILY(0),
    WEEKLY(1),
    MONTHLY(2);

    private int id;

    Frequency(int id) { this.id = id; }

    public static Frequency getFromId(int id) {
        return switch (id) {
            case 0 -> DAILY;
            case 1 -> WEEKLY;
            case 2 -> MONTHLY;
            default -> DAILY;
        };
    }

}
