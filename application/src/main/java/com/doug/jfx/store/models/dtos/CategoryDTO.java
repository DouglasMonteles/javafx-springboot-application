package com.doug.jfx.store.models.dtos;

import com.doug.jfx.store.models.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class CategoryDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -8303764896783458234L;

    private Long id;

    private String name;

    public CategoryDTO(Category category) {
        this(category.getId(), category.getName());
    }

    /*
    * Método utilizado no MFXCheckListView para exibir somente a
    * propriedade do nome, mas o valor será o objeto inteiro
    * */
    @Override
    public String toString() {
        return name;
    }

}
