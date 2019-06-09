package com.smile.prebookingforgames.error;

import com.smile.prebookingforgames.dto.ResultError;
import org.springframework.http.HttpStatus;

public enum ServiceError {
    PHONE_NUMBER_DUPLICATE("4001","phone number already register", HttpStatus.CONFLICT),
    VALIDATION_CHECK_ERROR("4002","phone number or privateYn check error", HttpStatus.NOT_ACCEPTABLE),
    WRONG_PHONE_NUMBER("4003","wrong phone number", HttpStatus.LENGTH_REQUIRED),
    INTERNAL_SERVER_ERROR("5000","internal server error", HttpStatus.INTERNAL_SERVER_ERROR);

    public String resultCode;
    public String resultMessage;
    public HttpStatus httpStatus;

    ServiceError(String resultCode,String resultMessage, HttpStatus httpStatus) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.httpStatus = httpStatus;
    }

    public String getResultCode() {
        return this.resultCode;
    }

    public String getResultMessage() {
        return this.resultMessage;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public ResultError getResultError(){
        ResultError resultError = ResultError.builder()
                                .resultCode(getResultCode())
                                .resultMessage(getResultMessage())
                                .httpStatus(getHttpStatus())
                                .build();
        return resultError;
    };

}
