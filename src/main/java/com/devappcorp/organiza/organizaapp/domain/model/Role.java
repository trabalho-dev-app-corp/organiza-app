package com.devappcorp.organiza.organizaapp.domain.model;

import java.util.Set;

import com.google.common.collect.Sets;

public enum Role {
    USUARIO(Sets.newHashSet()), 
    ADMIN(Sets.newHashSet(Permission.EVENTO_WRITE)),
    ORGANIZADOR(Sets.newHashSet());

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
