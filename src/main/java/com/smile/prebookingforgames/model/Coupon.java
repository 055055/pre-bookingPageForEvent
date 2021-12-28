package com.smile.prebookingforgames.model;

import com.smile.prebookingforgames.dto.CouponIssueDto;
import com.smile.prebookingforgames.entity.CouponEntity;
import com.smile.prebookingforgames.repository.CouponRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class Coupon {
    private final CouponRepository couponRepository;

    protected boolean isDuplicatePhoneNumber(String phoneNumber) {
        return couponRepository.findByPhoneNumber(phoneNumber).isPresent();
    }

    protected boolean isDuplicateCoupon(String couponNumber) {
        return couponRepository.findByCouponNumber(couponNumber).isPresent();
    }

    protected CouponEntity saveEntity(CouponIssueDto couponIssueDto, String couponNumber){
        CouponEntity couponEntity = CouponEntity.builder()
                .phoneNumber(couponIssueDto.getPhoneNumber())
                .privateYn(couponIssueDto.isPrivateYn())
                .couponNumber(couponNumber)
                .build();
        return couponRepository.save(couponEntity);
    }

    public abstract CouponEntity issue(CouponIssueDto couponIssueDto);
}