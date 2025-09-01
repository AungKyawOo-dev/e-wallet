package com.aungkyawoo.wallet_service.entity;

import com.aungkyawoo.wallet_service.constants.TransactionType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "wallet_transactions")
@Getter
@Setter
@Builder
public class Transactions extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromUserId;

    private String toUserId;

    private BigDecimal amount;

    private String currency;

    @Enumerated(EnumType.STRING)
    private TransactionType type; // DEPOSIT, WITHDRAW, TRANSFER_IN, TRANSFER_OUT

    private String reference; // Optional (transaction ID, notes)

}

