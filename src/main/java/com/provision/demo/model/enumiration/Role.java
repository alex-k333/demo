package com.provision.demo.model.enumiration;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    MANAGER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
