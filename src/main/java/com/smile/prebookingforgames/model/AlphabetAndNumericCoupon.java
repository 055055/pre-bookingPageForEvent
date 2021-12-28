package com.smile.prebookingforgames.model;

import com.smile.prebookingforgames.dto.CouponIssueDto;
import com.smile.prebookingforgames.entity.CouponEntity;
import com.smile.prebookingforgames.error.CouponError;
import com.smile.prebookingforgames.exception.CouponException;
import com.smile.prebookingforgames.repository.CouponRepository;

import java.util.Random;

public class AlphabetAndNumericCoupon extends Coupon {

    public AlphabetAndNumericCoupon(CouponRepository couponRepository) {
        super(couponRepository);
    }

    @Override
    public CouponEntity issue(CouponIssueDto.Request couponIssueDto) {
        if (isDuplicatePhoneNumber(couponIssueDto.getPhoneNumber())) throw new CouponException(CouponError.PHONE_NUMBER_DUPLICATE);

        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        int i = 0;
        boolean check = true;
        while (check) {
            if (random.nextBoolean()) {
                if (random.nextBoolean()) {
                    sb.append((char) ((random.nextInt(26)) + 97)); //랜덤 소문자
                } else {
                    sb.append((char) ((random.nextInt(26)) + 65)); //랜덤 대문자
                }
            } else {
                sb.append((random.nextInt(10)));
            }

            if (i == 3 || i == 7) {
                sb.append("-");
            }
            i++;
            if (i == 12) {
                if (isDuplicateCoupon(sb.toString())) {
                    i = 0;
                } else {
                    check = false;
                }
            }
        }
        return saveEntity(couponIssueDto, sb.toString());
    }
}