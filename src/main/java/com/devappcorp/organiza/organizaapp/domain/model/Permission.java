package com.devappcorp.organiza.organizaapp.domain.model;

public enum Permission {
    EVENTO_WRITE("evento:write");

    private final String permissionString;

    Permission(String permissionString) {
        this.permissionString = permissionString;
    }

    public String getPermissionString() {
        return permissionString;
    }
}
