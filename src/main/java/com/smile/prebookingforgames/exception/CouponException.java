package com.smile.prebookingforgames.exception;

import com.smile.prebookingforgames.error.CouponError;
import lombok.Getter;

@Getter
public class CouponException extends RuntimeException {
    private final CouponError couponError;

    public CouponException(CouponError couponError) {
        super(couponError.getMessage());
        this.couponError = couponError;
    }
}