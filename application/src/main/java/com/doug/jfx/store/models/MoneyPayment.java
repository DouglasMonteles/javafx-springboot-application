package com.doug.jfx.store.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tb_money_payment")
public class MoneyPayment extends Payment {

    @Serial
    private static final long serialVersionUID = -6929375135947727959L;

    @Column(nullable = false)
    private BigDecimal change;

    @Override
    public String description() {
        return "DINHEIRO";
    }
}

