package com.aungkyawoo.wallet_service.exception;

import com.aungkyawoo.wallet_service.dto.response.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Global Exception Handler
 * Author : Aung Kyaw Oo
 */
@ControllerAdvice
public class  GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle Method Argument Not Valid Exception
     * @param ex MethodArgumentNotValidException
     * @param headers HttpHeaders
     * @param status HttpStatusCode
     * @param request WebRequest
     * @return ResponseEntity
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle Global Exception
     * @param ex Exception
     * @param webRequest WebRequest
     * @return ResponseEntity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception ex, WebRequest webRequest) {

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle User Already Exists Exception
     * @param ex UserAlreadyExistsException
     * @param webRequest WebRequest
     * @return ResponseEntity
     */
    @ExceptionHandler(WalletAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleWalletAlreadyExistsException(WalletAlreadyExistsException ex, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle Resource Not Found Exception
     * @param ex ResourceNotFoundException
     * @param webRequest WebRequest
     * @return ResponseEntity
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest) {

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle Wallet Not Found Exception
     * @param ex WalletNotFoundException
     * @param webRequest WebRequest
     * @return ResponseEntity
     */
    @ExceptionHandler(WalletNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleWalletNotFoundException(WalletNotFoundException ex, WebRequest webRequest) {

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateTransactionException.class)
    public ResponseEntity<ErrorResponseDto> handleDuplicateTransactionException(DuplicateTransactionException ex, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.CONFLICT,
                ex.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ErrorResponseDto> handleInsufficientBalanceException(InsufficientBalanceException ex, WebRequest webRequest) {

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

}
