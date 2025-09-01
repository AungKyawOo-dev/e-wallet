package com.aungkyawoo.user_service.client;

import com.aungkyawoo.user_service.constants.ClientConstants;
import com.aungkyawoo.user_service.dto.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static com.aungkyawoo.user_service.constants.CurrencyConstants.DEFAULT_CURRENCY;

@Slf4j
@Component
@RequiredArgsConstructor
public class WalletClient {

    private final RestTemplate restTemplate;

    public void initWallet(String userId) {
        String url = ClientConstants.WALLET_CLIENT_GET_USER_BY_ID.replace("{id}", String.valueOf(userId)).replace("{currency}", DEFAULT_CURRENCY);
        log.info("url: {}", url);
        try {
            ResponseDto responseDto = restTemplate.postForObject(url, null, ResponseDto.class);
            log.info("Wallet initialized, response: {}", responseDto);
        } catch (Exception e) {
            log.error("error: {}", e.getMessage());
        }
    }
}
