package com.doug.jfx.store.builders;

import com.doug.jfx.store.enums.ProductMeasurement;
import com.doug.jfx.store.models.Category;
import com.doug.jfx.store.models.Product;
import com.doug.jfx.store.models.dtos.CategoryDTO;
import com.doug.jfx.store.models.dtos.PictureDTO;
import com.doug.jfx.store.models.dtos.ProductDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ProductBuilder {

    ProductBuilder setId(Long id);

    ProductBuilder setName(String name);

    ProductBuilder setDescription(String description);

    ProductBuilder setPrice(BigDecimal price);

    ProductBuilder setMeasurement(Integer measurementType);

    ProductBuilder setMeasurementType(ProductMeasurement productMeasurement);

    ProductBuilder setCategories(List<CategoryDTO> categories);

    ProductBuilder setPictures(List<PictureDTO> pictures);

    ProductBuilder isAvailable(Boolean isAvailable);

    Product build();

    ProductDTO buildAndConvertToDTO();

}
