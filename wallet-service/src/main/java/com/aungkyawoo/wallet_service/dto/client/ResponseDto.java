package com.aungkyawoo.wallet_service.dto.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Response Dto
 * Author : Aung Kyaw Oo
 */
@Data
@AllArgsConstructor
@Builder
public class ResponseDto {

    private String StatusCode;
    private String StatusMessage;
    private Object data;
}
