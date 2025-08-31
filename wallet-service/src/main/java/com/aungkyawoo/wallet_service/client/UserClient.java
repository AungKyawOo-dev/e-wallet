package com.aungkyawoo.wallet_service.client;

import com.aungkyawoo.wallet_service.constants.ClientConstants;
import com.aungkyawoo.wallet_service.dto.client.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserClient {

    private final RestTemplate restTemplate;

    public boolean checkUserExists(Long userId) {
        String url = ClientConstants.USER_CLIENT_GET_USER_BY_ID.replace("{id}", String.valueOf(userId));
        log.info("url: {}", url);
        try {
            ResponseDto responseDto = restTemplate.getForObject(url, ResponseDto.class);
            log.info("responseDto: {}", responseDto);
            return responseDto.getData() != null;
        } catch (Exception e) {
            return false;
        }
    }
}
