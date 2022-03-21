package com.smile.prebookingforgames.service;

import com.smile.prebookingforgames.dto.CouponIssueDto;
import com.smile.prebookingforgames.dto.CouponIssuedListDto;
import com.smile.prebookingforgames.model.AlphabetAndNumericCoupon;
import com.smile.prebookingforgames.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final CouponRepository couponRepository;

    @Override
    public CouponIssueDto issueCoupon(CouponIssueDto.Request registerDTO) {

        return CouponIssueDto.Response
                .builder()
                .couponNumber(
                        new AlphabetAndNumericCoupon(couponRepository)
                                .issue(registerDTO)
                                .getCouponNumber()
                        )
                .build();
    }

    @Override
    public List<CouponIssuedListDto> getAllCoupons() {

        return new AlphabetAndNumericCoupon(couponRepository)
                .findAll()
                .stream()
                .map(coupon ->
                        CouponIssuedListDto.builder()
                                .couponSeq(coupon.getSeq())
                                .couponNumber(coupon.getCouponNumber())
                                .phoneNumber(coupon.getPhoneNumber())
                                .privateYn(coupon.isPrivateYn())
                                .regDate(coupon.getRegDate())
                                .build()
                ).collect(Collectors.toList());
    }
}
