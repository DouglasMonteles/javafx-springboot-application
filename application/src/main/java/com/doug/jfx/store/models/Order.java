package com.doug.jfx.store.models;

import com.doug.jfx.store.models.dtos.OrderDTO;
import com.doug.jfx.store.models.dtos.UserDTO;
import jakarta.persistence.*;
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

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 863508344945801384L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Instant date;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false, foreignKey = @ForeignKey(name = "FK_ORDER_CLIENT"))
    private User client;

    @OneToOne(mappedBy = "order")
    private Payment payment;

    @OneToMany(mappedBy = "id.order")
    private List<OrderedItem> orderedItems = new ArrayList<>();

    public Order(OrderDTO orderDTO) {
        this.id = orderDTO.getId();
        this.date = orderDTO.getDate();
        this.client = new User(orderDTO.getClient());
        this.payment = orderDTO.getPayment();
        this.orderedItems = orderDTO.getOrderedItems();
    }

    public BigDecimal getTotal() {
        return orderedItems.stream()
                .map(OrderedItem::getPrice)
                .reduce(new BigDecimal(0), BigDecimal::add);
    }

}
