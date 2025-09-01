package com.aungkyawoo.wallet_service.dto.request;

import com.aungkyawoo.wallet_service.constants.CurrencyConstants;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "From User ID cannot be null")
    private String fromUserId;
    @NotNull(message = "To User ID cannot be null")
    private String toUserId;
    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.01", message = "Deposit amount must be greater than zero")
    private BigDecimal amount;
    private String currency;
    @NotNull(message = "Reference cannot be null")
    private String reference;

    @AssertTrue(message = "Unsupported currency")
    public boolean isCurrencyValid() {
        return CurrencyConstants.SUPPORTED_CURRENCIES.contains(currency);
    }
}
