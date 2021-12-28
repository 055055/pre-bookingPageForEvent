package com.smile.prebookingforgames.service;

import com.smile.prebookingforgames.dto.CouponIssueDto;
import com.smile.prebookingforgames.dto.CouponIssuedListDto;

import java.util.List;

public interface EventService {
    CouponIssueDto issueCoupon(CouponIssueDto.Request registerDTO);
    List<CouponIssuedListDto> findAllCoupon();
}
