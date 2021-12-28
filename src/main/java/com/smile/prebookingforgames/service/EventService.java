package com.smile.prebookingforgames.service;

import com.smile.prebookingforgames.dto.CouponListDTO;
import com.smile.prebookingforgames.dto.CouponIssueDto;

import java.util.List;
import java.util.Map;

public interface EventService {
    Map<String,String> registerCoupon(CouponIssueDto registerDTO);
    List<CouponListDTO> getCouponList();
}
