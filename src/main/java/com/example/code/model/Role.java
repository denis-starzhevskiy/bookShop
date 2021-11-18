package com.example.code.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    user(Collections.unmodifiableSet(new HashSet<>(Collections.singletonList(Permission.DEVELOPERS_READ)))),
    admin(Collections.unmodifiableSet(new HashSet<>(Arrays.asList(Permission.DEVELOPERS_READ, Permission.DEVELOPERS_WRITE))));


    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
