package com.harunisik.cakes.exception;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CakeManagerException.class)
    @Order(HIGHEST_PRECEDENCE)
    @ResponseBody
    public ResponseEntity<CakeManagerExceptionResponse> handleException(CakeManagerException exception) {
        log.error("Error: ", exception);
        ExceptionType exceptionType = exception.getExceptionType();
        CakeManagerExceptionResponse cakeManagerExceptionResponse = convertToResponse(exceptionType);
        return new ResponseEntity<>(cakeManagerExceptionResponse, exceptionType.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<CakeManagerExceptionResponse> handleAllExceptions(Exception exception) {
        log.error("Error: ", exception);
        CakeManagerExceptionResponse cakeManagerExceptionResponse = convertToResponse(ExceptionType.INTERNAL_ERROR);
        return new ResponseEntity<>(cakeManagerExceptionResponse, INTERNAL_SERVER_ERROR);
    }

    private CakeManagerExceptionResponse convertToResponse(ExceptionType exceptionType) {
        return CakeManagerExceptionResponse.builder()
            .errorCode(exceptionType.getCode())
            .description(exceptionType.getMessage())
            .timestamp(Timestamp.valueOf(LocalDateTime.now()))
            .build();
    }

}