package com.aungkyawoo.wallet_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DepositRequestDto {

    private Long userId;
    private BigDecimal amount;
    private String reference;

}