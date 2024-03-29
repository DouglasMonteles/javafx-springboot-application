package com.doug.jfx.store.models.dtos;

import com.doug.jfx.store.models.MoneyPayment;
import com.doug.jfx.store.models.Order;
import com.doug.jfx.store.models.OrderedItem;
import com.doug.jfx.store.models.Payment;
import com.doug.jfx.store.utils.DateUtils;
import com.doug.jfx.store.utils.PriceUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class OrderListTableDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 5938346757739162919L;

    private Long id;
    private String date;
    private String clientName;
    private String payment;
    private String status;
    private String total;
    private List<String> orderedItems = new ArrayList<>();

    public OrderListTableDTO(OrderDTO order) {
        this.id = order.getId();
        this.date = DateUtils.dateTimePtBr(order.getDate());
        this.clientName = order.getClient().getName();
        this.payment = order.getPayment().description();
        this.status = order.getPayment().getStatus().name();
        this.total = PriceUtils.pricePtBr(order.getTotal());

        this.orderedItems.addAll(
                order.getOrderedItems().stream()
                        .map(it -> {
                            var prod = it.getProduct();
                            return "Nome: " + prod.getName() + " - " +
                                    "Preço: " + PriceUtils.pricePtBr(prod.getPrice()) + " - " +
                                    "Qtd.: " + it.getQuantity() + " - " +
                                    "Desc.: " + PriceUtils.pricePtBr(it.getDiscount()) + " - " +
                                    "Tot.: " + PriceUtils.pricePtBr(it.getPrice());
                        }).toList()
        );
    }
}
