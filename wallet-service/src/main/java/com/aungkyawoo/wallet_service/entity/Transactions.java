package com.aungkyawoo.wallet_service.entity;

import com.aungkyawoo.wallet_service.constants.TransactionType;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "transactions")

public class Transactions extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type; // DEPOSIT, WITHDRAW, TRANSFER_IN, TRANSFER_OUT

    private String reference; // Optional (transaction ID, notes)

}

