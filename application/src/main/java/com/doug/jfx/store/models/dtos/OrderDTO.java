package com.doug.jfx.store.models.dtos;

import com.doug.jfx.store.models.Order;
import com.doug.jfx.store.models.OrderedItem;
import com.doug.jfx.store.models.Payment;
import com.doug.jfx.store.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class OrderDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5162937182415731600L;

    private Long id;
    private Instant date;
    private UserDTO client;
    private Payment payment;
    private List<OrderedItem> orderedItems = new ArrayList<>();

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.date = order.getDate();
        this.client = new UserDTO(order.getClient());
        this.payment = order.getPayment();
        this.orderedItems = order.getOrderedItems();
    }

}
