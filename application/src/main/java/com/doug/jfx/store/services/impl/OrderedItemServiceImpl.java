package com.doug.jfx.store.services.impl;

import com.doug.jfx.store.models.OrderedItem;
import com.doug.jfx.store.models.dtos.CartTupleDTO;
import com.doug.jfx.store.services.OrderedItemService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderedItemServiceImpl implements OrderedItemService {

    private final List<CartTupleDTO> cartItems = new ArrayList<>();

    @Override
    public void addCartItem(CartTupleDTO cartTupleDTO) {
        Long productId = cartTupleDTO.getOrderedItem().getProduct().getId();

        if (!isItemAlreadySelected(productId)) {
            this.cartItems.add(cartTupleDTO);
        } else {
            increaseQuantity(productId);
        }
    }

    @Override
    public void increaseQuantity(Long productId) {
        this.cartItems.stream()
                .filter(it -> it.getOrderedItem().getProduct().getId().equals(productId))
                .findFirst()
                .ifPresent(it -> it.getOrderedItem().increaseQuantity());
    }

    @Override
    public void decreaseQuantity(Long productId) {
        this.cartItems.stream()
                .filter(it -> it.getOrderedItem().getProduct().getId().equals(productId))
                .findFirst()
                .ifPresent(it -> it.getOrderedItem().decreaseQuantity());
    }

    @Override
    public void removeCartItem(int index) {
        this.cartItems.remove(index);
    }

    @Override
    public void removeCartItem(List<Long> productsIds) {
        var itemsToRemove = this.cartItems.stream()
                .filter(it -> productsIds.contains(it.getOrderedItem().getProduct().getId()))
                .toList();

        this.cartItems.removeAll(itemsToRemove);
    }

    @Override
    public void clearCartItems() {
        this.cartItems.clear();
    }

    @Override
    public List<CartTupleDTO> getCartItems() {
        return cartItems;
    }

    @Override
    public BigDecimal getTotal() {
        return cartItems.stream()
                .map(it -> it.getOrderedItem().getPrice())
                .reduce(new BigDecimal(0), BigDecimal::add);
    }

    @Override
    public int findIndex(Long productId) {
        for (int i = 0; i < cartItems.size(); i++) {
            if (cartItems.get(i).getOrderedItem().getProduct().getId().equals(productId)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public OrderedItem getOrderedItem(long productId) {
        return cartItems.get(findIndex(productId)).getOrderedItem();
    }

    @Override
    public OrderedItem getOrderedItem(int index) {
        return cartItems.get(index).getOrderedItem();
    }

    public boolean isItemAlreadySelected(Long productId) {
        return !cartItems.stream()
                .filter(it -> it.getOrderedItem().getProduct().getId().equals(productId))
                .toList().isEmpty();
    }


}
