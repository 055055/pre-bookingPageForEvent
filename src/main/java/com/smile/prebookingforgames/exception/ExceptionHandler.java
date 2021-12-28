package com.smile.prebookingforgames.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(PreBookingException.class)
    public ResponseEntity<?> PreBookingExceptionHandler(PreBookingException e){
        return new ResponseEntity(e.getServiceError().getResultError(), e.getServiceError().getHttpStatus());
    }
}
