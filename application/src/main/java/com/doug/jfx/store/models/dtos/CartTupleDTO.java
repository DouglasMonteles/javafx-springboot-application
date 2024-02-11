package com.doug.jfx.store.models.dtos;

import com.doug.jfx.store.models.OrderedItem;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CartTupleDTO {

    private OrderedItem orderedItem;

    private MFXCheckbox productDTOCheckbox;

}
