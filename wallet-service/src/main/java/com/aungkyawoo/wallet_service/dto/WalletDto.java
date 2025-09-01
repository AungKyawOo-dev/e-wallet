package com.aungkyawoo.wallet_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletDto {

    private String userId;
    private BigDecimal balance;
    private String currency;

}
