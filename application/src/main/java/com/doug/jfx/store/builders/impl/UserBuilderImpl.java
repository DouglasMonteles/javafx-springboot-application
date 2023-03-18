package com.doug.jfx.store.builders.impl;

import com.doug.jfx.store.builders.UserBuilder;
import com.doug.jfx.store.models.Role;
import com.doug.jfx.store.models.User;
import com.doug.jfx.store.models.dtos.UserDTO;

import java.util.List;

public class UserBuilderImpl implements UserBuilder {

    private final User user;

    public UserBuilderImpl() {
        this.user = new User();
    }

    @Override
    public UserBuilder setId(Long id) {
        this.user.setId(id);
        return this;
    }

    @Override
    public UserBuilder setName(String name) {
        this.user.setName(name);
        return this;
    }

    @Override
    public UserBuilder setEmail(String email) {
        this.user.setEmail(email);
        return this;
    }

    @Override
    public UserBuilder setPassword(String password) {
        this.user.setPassword(password);
        return this;
    }

    @Override
    public UserBuilder setPhone1(String phone1) {
        this.user.getPhones().add(phone1);
        return this;
    }

    @Override
    public UserBuilder setPhone2(String phone2) {
        this.user.getPhones().add(phone2);
        return this;
    }

    @Override
    public UserBuilder setRoles(List<Role> roles) {
        this.user.getRoles().addAll(roles);
        return this;
    }

    @Override
    public UserBuilder isActive(boolean isActive) {
        return this;
    }

    @Override
    public User build() {
        return user;
    }

    @Override
    public UserDTO buildAndConvertToDTO() {
        return new UserDTO(user);
    }
}
