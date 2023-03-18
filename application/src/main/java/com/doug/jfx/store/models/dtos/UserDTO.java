package com.doug.jfx.store.models.dtos;

import com.doug.jfx.store.models.Role;
import com.doug.jfx.store.models.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public record UserDTO(
        Long id,
        String name,
        String email,
        String password,
        List<String> roles,
        boolean isActive
) implements Serializable {

    @Serial
    private static final long serialVersionUID = -6844105396127645907L;

    public UserDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getRoles().stream().map(Role::getAuthority).toList(), user.isActive());
    }

}
