package com.aungkyawoo.wallet_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransferRequestDto {

    private Long fromUserId;
    private Long toUserId;
    private BigDecimal amount;
    private String reference;
}
