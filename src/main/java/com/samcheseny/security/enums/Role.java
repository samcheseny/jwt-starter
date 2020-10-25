package com.samcheseny.security.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.samcheseny.security.enums.Permission.*;

public enum Role {

    STUDENT(Set.of(COURSE_READ, STUDENT_READ, STUDENT_UPDATE)),
    ADMIN(Set.of(COURSE_CREATE, COURSE_READ, COURSE_UPDATE, COURSE_DELETE, STUDENT_CREATE, STUDENT_READ, STUDENT_DELETE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {

        Set<SimpleGrantedAuthority> permissions = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return permissions;
    }
}
