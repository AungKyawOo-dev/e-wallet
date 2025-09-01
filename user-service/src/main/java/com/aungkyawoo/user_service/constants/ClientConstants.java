package com.aungkyawoo.user_service.constants;

public class ClientConstants {

    public static final String WALLET_CLIENT_BASE_URL = "http://localhost:8082/api/v1/wallets/";

    public static final String WALLET_CLIENT_GET_USER_BY_ID = WALLET_CLIENT_BASE_URL + "{id}" + "/init?currency={currency}";
}
