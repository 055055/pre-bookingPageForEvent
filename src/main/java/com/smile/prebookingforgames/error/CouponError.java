package com.smile.prebookingforgames.error;

import com.smile.prebookingforgames.dto.ResultError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum CouponError {
    REQUEST_VALIDATION("4000", "요청 값을 다시 확인해 주세요", HttpStatus.BAD_REQUEST),
    PHONE_NUMBER_DUPLICATE("4001", "이미 요청한 휴대폰 번호 입니다.", HttpStatus.CONFLICT),
    INTERNAL_SERVER_ERROR("5000", "internal server error", HttpStatus.INTERNAL_SERVER_ERROR);

    public String code;
    public String message;
    public HttpStatus httpStatus;

    public ResultError getResultError() {
        return ResultError.builder()
                .code(getCode())
                .message(getMessage())
                .build();
    }
}