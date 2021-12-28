package com.smile.prebookingforgames.service;

import com.smile.prebookingforgames.dto.CouponIssueDto;
import com.smile.prebookingforgames.dto.CouponIssuedListDto;
import com.smile.prebookingforgames.entity.CouponEntity;
import com.smile.prebookingforgames.model.AlphabetAndNumericCoupon;
import com.smile.prebookingforgames.model.Coupon;
import com.smile.prebookingforgames.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final CouponRepository couponRepository;

    @Override
    public CouponIssueDto issueCoupon(CouponIssueDto.Request registerDTO) {

        Coupon coupon = new AlphabetAndNumericCoupon(couponRepository);
        CouponEntity issue = coupon.issue(registerDTO);
        return CouponIssueDto.Response.builder().couponNumber(issue.getCouponNumber()).build();
    }

    @Override
    public List<CouponIssuedListDto> findAllCoupon() {
        List<CouponIssuedListDto> result = new ArrayList<>();
        Coupon coupon = new AlphabetAndNumericCoupon(couponRepository);

        for (CouponEntity couponEntity : coupon.findAll()) {
            result.add(
                    CouponIssuedListDto.builder()
                            .couponSeq(couponEntity.getCouponSeq())
                            .couponNumber(couponEntity.getCouponNumber())
                            .phoneNumber(couponEntity.getPhoneNumber())
                            .privateYn(couponEntity.isPrivateYn())
                            .regDate(couponEntity.getRegDate())
                            .build()
            );
        }
        return result;
    }
}