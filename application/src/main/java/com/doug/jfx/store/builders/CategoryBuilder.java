package com.doug.jfx.store.builders;

import com.doug.jfx.store.models.Category;
import com.doug.jfx.store.models.User;
import com.doug.jfx.store.models.dtos.CategoryDTO;
import com.doug.jfx.store.models.dtos.UserDTO;

import java.util.List;

public interface CategoryBuilder {

    CategoryBuilder setId(Long id);

    CategoryBuilder setName(String name);

    Category build();

    CategoryDTO buildAndConvertToDTO();

}
