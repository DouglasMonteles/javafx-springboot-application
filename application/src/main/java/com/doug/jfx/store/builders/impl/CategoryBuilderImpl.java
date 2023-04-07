package com.doug.jfx.store.builders.impl;

import com.doug.jfx.store.builders.CategoryBuilder;
import com.doug.jfx.store.builders.UserBuilder;
import com.doug.jfx.store.models.Category;
import com.doug.jfx.store.models.Role;
import com.doug.jfx.store.models.User;
import com.doug.jfx.store.models.dtos.CategoryDTO;
import com.doug.jfx.store.models.dtos.UserDTO;

import java.util.List;

public class CategoryBuilderImpl implements CategoryBuilder {

    private final Category category;

    public CategoryBuilderImpl() {
        this.category = new Category();
    }

    @Override
    public CategoryBuilder setId(Long id) {
        this.category.setId(id);
        return this;
    }

    @Override
    public CategoryBuilder setName(String name) {
        this.category.setName(name);
        return this;
    }

    @Override
    public Category build() {
        return category;
    }

    @Override
    public CategoryDTO buildAndConvertToDTO() {
        return new CategoryDTO(category);
    }

}
