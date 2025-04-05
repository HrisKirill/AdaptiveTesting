package com.khrystoforov.adaptivetesting.user.model;

public enum Role {
    USER, TEACHER;

    Role() {
    }

    public static Role getRole(String name) {
        for (Role role : Role.values()) {
            if (name.equalsIgnoreCase(role.name())) {
                return role;
            }
        }

        return USER;
    }
}
