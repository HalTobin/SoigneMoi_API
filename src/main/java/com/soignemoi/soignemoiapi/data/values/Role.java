package com.soignemoi.soignemoiapi.data.values;

public enum Role {
    VISITOR("Visitor"),
    DOCTOR("Doctor"),
    SECRETARY("Secretary"),
    ADMIN("Admin");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
