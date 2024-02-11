package com.doug.jfx.store.services.impl;

import com.doug.jfx.store.models.OrderedItem;
import com.doug.jfx.store.repositories.OrderedItemRepository;
import com.doug.jfx.store.services.OrderedItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderedItemServiceImpl implements OrderedItemService {

    private final List<OrderedItem> cartItems = new ArrayList<>();

    @Autowired
    private OrderedItemRepository orderedItemRepository;


    @Override
    public void addCartItem(OrderedItem orderedItem) {
        Long productId = orderedItem.getProduct().getId();

        if (!isItemAlreadySelected(productId)) {
            this.cartItems.add(orderedItem);
        } else {
            increaseQuantity(productId);
        }
    }

    @Override
    public void increaseQuantity(Long productId) {
        this.cartItems.stream()
                .filter(it -> it.getProduct().getId().equals(productId))
                .findFirst()
                .ifPresent(OrderedItem::increaseQuantity);
    }

    @Override
    public void decreaseQuantity(Long productId) {
        this.cartItems.stream()
                .filter(it -> it.getProduct().getId().equals(productId))
                .findFirst()
                .ifPresent(OrderedItem::decreaseQuantity);
    }

    @Override
    public void addCartItem(List<OrderedItem> orderedItems) {
        orderedItems.forEach(this::addCartItem);
    }

    @Override
    public void removeCartItem(int index) {
        this.cartItems.remove(index);
    }

    @Override
    public void removeCartItem(List<Long> productsIds) {
        var itemsToRemove = this.cartItems.stream()
                .filter(it -> productsIds.contains(it.getProduct().getId()))
                .toList();

        this.cartItems.removeAll(itemsToRemove);
    }

    @Override
    public void clearCartItems() {
        this.cartItems.clear();
    }

    @Override
    public List<OrderedItem> getCartItems() {
        return cartItems;
    }

    @Override
    public BigDecimal getTotal() {
        return cartItems.stream()
                .map(OrderedItem::getPrice)
                .reduce(new BigDecimal(0), BigDecimal::add);
    }

    @Override
    public int findIndex(Long productId) {
        for (int i = 0; i < cartItems.size(); i++) {
            if (cartItems.get(i).getProduct().getId().equals(productId)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public OrderedItem getOrderedItem(long productId) {
        return cartItems.get(findIndex(productId));
    }

    @Override
    public OrderedItem getOrderedItem(int index) {
        return cartItems.get(index);
    }

    public boolean isItemAlreadySelected(Long productId) {
        return !cartItems.stream()
                .filter(it -> it.getProduct().getId().equals(productId))
                .toList().isEmpty();
    }


}
