package com.soignemoi.soignemoiapi.error;

public class ValueNotFoundException extends Exception {
    public ValueNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
