package com.doug.jfx.store.builders.impl;

import com.doug.jfx.store.builders.ProductBuilder;
import com.doug.jfx.store.enums.ProductMeasurement;
import com.doug.jfx.store.models.Category;
import com.doug.jfx.store.models.Picture;
import com.doug.jfx.store.models.Product;
import com.doug.jfx.store.models.dtos.CategoryDTO;
import com.doug.jfx.store.models.dtos.PictureDTO;
import com.doug.jfx.store.models.dtos.ProductDTO;

import java.math.BigDecimal;
import java.util.List;

public class ProductBuilderImpl implements ProductBuilder {

    private final Product product;

    public ProductBuilderImpl() {
        this.product = new Product();
    }

    public ProductBuilder setId(Long id) {
        this.product.setId(id);
        return this;
    }

    public ProductBuilder setName(String name) {
        this.product.setName(name);
        return this;
    }

    public ProductBuilder setDescription(String description) {
        this.product.setDescription(description);
        return this;
    }

    public ProductBuilder setPrice(BigDecimal price) {
        this.product.setPrice(price);
        return this;
    }

    public ProductBuilder setMeasurement(Integer measurementType) {
        this.product.setMeasurement(measurementType);
        return this;
    }

    public ProductBuilder setMeasurementType(ProductMeasurement productMeasurement) {
        this.product.setMeasurementType(productMeasurement);
        return this;
    }

    public ProductBuilder setCategories(List<CategoryDTO> categories) {
        this.product
                .getCategories()
                .addAll(categories.stream().map(Category::new).toList());
        return this;
    }

    public ProductBuilder setPictures(List<PictureDTO> pictures) {
        this.product
                .getPictures()
                .addAll(pictures.stream().map(Picture::new).toList());
        return this;
    }

    public ProductBuilder isAvailable(Boolean isAvailable) {
        this.product.setAvailable(isAvailable);
        return this;
    }

    public Product build() {
        return product;
    }

    public ProductDTO buildAndConvertToDTO() {
        return new ProductDTO(product);
    }

}
