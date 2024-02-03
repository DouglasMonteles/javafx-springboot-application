package com.doug.jfx.store.models;

import com.doug.jfx.store.enums.ProductMeasurement;
import com.doug.jfx.store.models.dtos.ProductDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "tb_product")
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = 5816938076805836512L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private ProductMeasurement measurementType;

    @Column(nullable = false)
    private Integer measurement;

    private boolean isAvailable = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_product_category",
            joinColumns = @JoinColumn(name = "product_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "category_id", nullable = false)
    )
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<Picture> pictures = new ArrayList<>();

    @OneToMany(mappedBy = "id.product")
    @Transient
    private final List<Product> orders = new ArrayList<>();

    public Product(ProductDTO productDTO) {
        this.id = productDTO.getId();
        this.name = productDTO.getName();
        this.price = productDTO.getPrice();
        this.description = productDTO.getDescription();
        this.measurementType = productDTO.getMeasurementType();
        this.measurement = productDTO.getMeasurement();
        this.isAvailable = productDTO.isAvailable();

        this.categories = productDTO.getCategories().stream().map(Category::new).toList();
        this.pictures = productDTO.getPictures().stream().map(Picture::new).toList();
    }

}
