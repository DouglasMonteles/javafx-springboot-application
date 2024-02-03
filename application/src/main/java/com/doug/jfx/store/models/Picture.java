package com.doug.jfx.store.models;

import com.doug.jfx.store.enums.PictureType;
import com.doug.jfx.store.models.dtos.PictureDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_picture")
public class Picture implements Serializable {

    @Serial
    private static final long serialVersionUID = 8819041721687862883L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    private PictureType type;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "FK_PRODUCT_IMAGE"))
    private Product product;

    @Transient
    private File picture;

    public Picture(PictureDTO pictureDTO) {
        this(pictureDTO.getId(), pictureDTO.getPath(), pictureDTO.getType(), pictureDTO.getProduct(), pictureDTO.getPicture());
    }

}
