package com.doug.jfx.store.models.dtos;

import com.doug.jfx.store.models.Role;
import com.doug.jfx.store.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class UserDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -6844105396127645907L;

    private Long id;
    private String name;
    private String email;
    private String password;
    private List<String> roles;
    boolean isActive;

    public UserDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getRoles().stream().map(Role::getAuthority).toList(), user.isActive());
    }

}
