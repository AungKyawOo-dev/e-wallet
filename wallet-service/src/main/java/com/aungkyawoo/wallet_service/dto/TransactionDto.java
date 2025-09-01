package com.aungkyawoo.wallet_service.dto;

import com.aungkyawoo.wallet_service.constants.TransactionType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TransactionDto {
    String fromUserId;
    String toUserId;
    BigDecimal amount;
    String reference;
    TransactionType type;
    LocalDateTime createdAt;
}
