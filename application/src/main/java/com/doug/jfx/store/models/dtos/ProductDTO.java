package com.doug.jfx.store.models.dtos;

import com.doug.jfx.store.enums.ProductMeasurement;
import com.doug.jfx.store.models.Picture;
import com.doug.jfx.store.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class ProductDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 5816938076805836512L;

    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private ProductMeasurement measurementType;
    private Integer measurement;
    private boolean isAvailable = true;

    private List<CategoryDTO> categories = new ArrayList<>();
    private List<PictureDTO> pictures = new ArrayList<>();

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.measurementType = product.getMeasurementType();
        this.measurement = product.getMeasurement();
        this.isAvailable = product.isAvailable();

        this.categories = product.getCategories().stream().map(CategoryDTO::new).toList();
        this.pictures = product.getPictures().stream().map(PictureDTO::new).toList();
    }

}
