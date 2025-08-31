package com.aungkyawoo.wallet_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Error Response Dto
 * Author : Aung Kyaw Oo
 */
@Data
@AllArgsConstructor
public class ErrorResponseDto {

    private String apiPath;

    private HttpStatus statusCode;

    private String errorMessage;

    private LocalDateTime errorTime;
}