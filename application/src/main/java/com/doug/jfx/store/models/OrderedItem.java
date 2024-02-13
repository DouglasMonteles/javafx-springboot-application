package com.doug.jfx.store.models;

import com.doug.jfx.store.utils.PriceUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_ordered_item")
public class OrderedItem implements Serializable {

    @Serial
    private static final long serialVersionUID = -1365910232755827006L;

    @EmbeddedId
    private OrderedItemPK id = new OrderedItemPK();

    @Column(nullable = false)
    private BigDecimal discount;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer quantity = 0;

    public OrderedItem(Order order, Product product,
                       BigDecimal discount, BigDecimal price, Integer quantity) {
        this.id.setOrder(order);
        this.id.setProduct(product);
        this.discount = discount;
        this.price = price;
        this.quantity = quantity;
    }

    public void setOrder(Order order) {
        this.id.setOrder(order);
    }

    public Order getOrder() {
        return this.id.getOrder();
    }

    public void setProduct(Product product) {
        this.id.setProduct(product);
    }

    public Product getProduct() {
        return this.id.getProduct();
    }

    public BigDecimal getPrice() {
        double priceWithDiscount = (price.doubleValue() - discount.doubleValue());
        return new BigDecimal(priceWithDiscount * quantity);
    }

    public void increaseQuantity() {
        this.quantity = this.quantity + 1;
    }

    public void decreaseQuantity() {
        if (this.quantity > 0) {
            this.quantity = this.quantity - 1;
        }
    }

    public String toString() {
        return "Produto: " + getProduct().getName() + "\n" +
                "Preço Unit.: " + PriceUtils.pricePtBr(getProduct().getPrice()) + " | " +
                "Qtd.: " + quantity + " | " +
                "Desconto: " + PriceUtils.pricePtBr(discount) + " | " +
                "\nPreço final: " + PriceUtils.pricePtBr(getPrice()) + "\n";
    }

}
