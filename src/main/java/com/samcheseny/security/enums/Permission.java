package com.samcheseny.security.enums;

public enum Permission {

    COURSE_CREATE("course_create"),
    COURSE_READ("course_read"),
    COURSE_UPDATE("course_update"),
    COURSE_DELETE("course_delete"),
    STUDENT_CREATE("student_create"),
    STUDENT_READ("student_read"),
    STUDENT_UPDATE("student_update"),
    STUDENT_DELETE("student_delete");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
