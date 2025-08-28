package com.aungkyawoo.user_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.net.http.HttpResponse;

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
