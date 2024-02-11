package com.doug.jfx.store.services;

import com.doug.jfx.store.models.OrderedItem;
import com.doug.jfx.store.models.dtos.CartTupleDTO;

import java.math.BigDecimal;
import java.util.List;

public interface OrderedItemService {

    void addCartItem(CartTupleDTO cartTupleDTO);

    void increaseQuantity(Long productId);

    void decreaseQuantity(Long productId);

    void removeCartItem(int index);

    void removeCartItem(List<Long> productsIds);

    void clearCartItems();

    List<CartTupleDTO> getCartItems();

    BigDecimal getTotal();

    int findIndex(Long productId);

    OrderedItem getOrderedItem(long productId);

    OrderedItem getOrderedItem(int index);

    boolean isItemAlreadySelected(Long productId);
}
