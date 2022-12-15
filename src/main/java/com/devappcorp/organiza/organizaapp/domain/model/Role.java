package com.devappcorp.organiza.organizaapp.domain.model;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

import net.bytebuddy.agent.builder.AgentBuilder.FallbackStrategy.Simple;

public enum Role {
    USUARIO(Sets.newHashSet()), 
    ADMIN(Sets.newHashSet()),
    ORGANIZADOR(Sets.newHashSet());

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrandeGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
        .map(permission -> new SimpleGrantedAuthority(permission.getPermissionString()))
        .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return permissions;
    }
}
