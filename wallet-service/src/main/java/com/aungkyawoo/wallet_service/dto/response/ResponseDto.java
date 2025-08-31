package com.aungkyawoo.wallet_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response Dto
 * Author : Aung Kyaw Oo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto {

    private String StatusCode;
    private String StatusMessage;
    private Object data;
}
