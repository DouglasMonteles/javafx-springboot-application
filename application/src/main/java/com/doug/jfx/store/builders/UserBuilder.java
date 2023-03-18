package com.doug.jfx.store.builders;

import com.doug.jfx.store.models.Role;
import com.doug.jfx.store.models.User;
import com.doug.jfx.store.models.dtos.UserDTO;

import java.util.List;

public interface UserBuilder {

    UserBuilder setId(Long id);

    UserBuilder setName(String name);

    UserBuilder setEmail(String email);

    UserBuilder setPassword(String password);

    UserBuilder setPhone1(String phone1);

    UserBuilder setPhone2(String phone2);

    UserBuilder setRoles(List<Role> roles);

    UserBuilder isActive(boolean isActive);

    User build();

    UserDTO buildAndConvertToDTO();

}
