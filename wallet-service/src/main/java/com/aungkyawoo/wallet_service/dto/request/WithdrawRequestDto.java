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
public class WithdrawRequestDto {

    private Long userId;
    private BigDecimal amount;
    private String reference;

}