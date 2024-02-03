package com.doug.jfx.store.models;

import com.doug.jfx.store.models.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_payment")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment implements Serializable {

    @Serial
    private static final long serialVersionUID = 2107660141219092067L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private PaymentStatus status;

    @OneToOne
    @JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "FK_ORDER_PAYMENT"))
    @MapsId
    private Order order;

}
