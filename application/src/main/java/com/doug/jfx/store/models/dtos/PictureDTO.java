package com.doug.jfx.store.models.dtos;

import com.doug.jfx.store.enums.PictureType;
import com.doug.jfx.store.models.Picture;
import com.doug.jfx.store.models.Product;
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
public class PictureDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 6626905976242780205L;

    private Long id;
    private String path;
    private PictureType type;
    private Product product;
    private File picture;

    public PictureDTO(Picture picture) {
        this(picture.getId(), picture.getPath(), picture.getType(), picture.getProduct(), picture.getPicture());
    }

}
