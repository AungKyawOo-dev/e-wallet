package com.aungkyawoo.wallet_service.constants;

import java.util.Set;

public class CurrencyConstants {
    public static final String USD = "USD";
    public static final String EUR = "EUR";
    public static final String JPY = "JPY";
    public static final String GBP = "GBP";
    public static final String AUD = "AUD";
    public static final String DEFAULT_CURRENCY = USD;

    // Optional: a list for validation
    public static final Set<String> SUPPORTED_CURRENCIES = Set.of(
            USD, EUR, JPY, GBP, AUD
    );

    private CurrencyConstants() {}
}
