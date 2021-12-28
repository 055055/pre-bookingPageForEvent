package com.smile.prebookingforgames.exception;

import com.smile.prebookingforgames.dto.ResultError;
import com.smile.prebookingforgames.error.ServiceError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class EventExceptionHandler {

    @ExceptionHandler(PreBookingException.class)
    public ResponseEntity<?> PreBookingExceptionHandler(PreBookingException e) {
        log.error("prebooking exception::{}", e.getServiceError());
        return new ResponseEntity<>(e.getServiceError().getResultError(), e.getServiceError().getHttpStatus());
    }

    /**
     * RequestBody Validation Exception Handler
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> responseMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return responseSpringValidException(e.getBindingResult());
    }

    /**
     * Parameter Validation Exception Handler
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> responseBindException(BindException e) {
        return responseSpringValidException(e.getBindingResult());
    }

    private ResponseEntity<?> responseSpringValidException(BindingResult bindingResult) {
        List<ResultError.FieldValue> fieldValues = new ArrayList<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            ResultError.FieldValue fieldValue = ResultError.FieldValue
                    .builder()
                    .field(fieldError.getField())
                    .value(fieldError.getRejectedValue())
                    .reason(fieldError.getDefaultMessage())
                    .build();
            fieldValues.add(fieldValue);
        }

        ResultError response = ResultError.builder()
                .code(ServiceError.REQUEST_VALIDATION.getCode())
                .message(ServiceError.REQUEST_VALIDATION.getMessage())
                .fieldValues(fieldValues)
                .build();

        log.error("spring valid exception:: code:{}, message:{}, fieldValues:{}", response.getCode(), response.getMessage(),
                Arrays.toString(response.getFieldValues().toArray()));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}