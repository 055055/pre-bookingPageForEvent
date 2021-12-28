package com.smile.prebookingforgames.error;

import com.smile.prebookingforgames.dto.ResultError;
import org.springframework.http.HttpStatus;

public enum ServiceError {
    REQUEST_VALIDATION("4000", "요청 값을 다시 확인해 주세요", HttpStatus.BAD_REQUEST),
    PHONE_NUMBER_DUPLICATE("4001","phone number already register", HttpStatus.CONFLICT),
    VALIDATION_CHECK_ERROR("4002","phone number or privateYn check error", HttpStatus.NOT_ACCEPTABLE),
    WRONG_PHONE_NUMBER("4003","wrong phone number", HttpStatus.LENGTH_REQUIRED),
    INTERNAL_SERVER_ERROR("5000","internal server error", HttpStatus.INTERNAL_SERVER_ERROR);

    public String code;
    public String message;
    public HttpStatus httpStatus;

    ServiceError(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public ResultError getResultError(){
        ResultError resultError = ResultError.builder()
                                .code(getCode())
                                .message(getMessage())
                                .build();
        return resultError;
    };

}
