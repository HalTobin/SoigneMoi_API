package com.soignemoi.soignemoiapi.data.values;

import lombok.Getter;

@Getter
public enum Role {
    VISITOR("Visitor"),
    DOCTOR("Doctor"),
    SECRETARY("Secretary"),
    ADMIN("Admin");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

}
