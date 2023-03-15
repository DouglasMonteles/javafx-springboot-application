package com.doug.jfx.store.enums;

public enum Role {

    ADMIN("ADMIN"),
    CLIENT("CLIENT"),
    SELLER("SELLER"),
    MANAGER("MANAGER");

    private final String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
