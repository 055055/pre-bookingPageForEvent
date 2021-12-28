package com.smile.prebookingforgames.service;

import com.smile.prebookingforgames.dto.CouponIssueDto;
import com.smile.prebookingforgames.dto.CouponListDTO;
import com.smile.prebookingforgames.entity.CouponEntity;
import com.smile.prebookingforgames.model.AlphabetAndNumericCoupon;
import com.smile.prebookingforgames.model.Coupon;
import com.smile.prebookingforgames.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final CouponRepository couponRepository;

    @Override
    public Map<String, String> issueCoupon(CouponIssueDto registerDTO) {

        Coupon coupon = new AlphabetAndNumericCoupon(couponRepository);
        CouponEntity issue = coupon.issue(registerDTO);
        Map<String, String> result = new HashMap();
        result.put("couponNumber", issue.getCouponNumber());
        return result;
    }

    @Override
    public List<CouponListDTO> findAllCoupon() {

        List<CouponListDTO> result = new ArrayList<>();

        List<CouponEntity> found = couponRepository.findAll();

        for (CouponEntity couponEntity : found) {
            CouponListDTO couponListDTO = CouponListDTO.builder()
                    .couponSeq(couponEntity.getCouponSeq())
                    .couponNumber(couponEntity.getCouponNumber())
                    .phoneNumber(couponEntity.getPhoneNumber())
                    .privateYn(couponEntity.isPrivateYn())
                    .regDate(couponEntity.getRegDate())
                    .build();
            result.add(couponListDTO);
        }
        return result;
    }
}