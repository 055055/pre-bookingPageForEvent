package com.smile.prebookingforgames.model;

import com.smile.prebookingforgames.dto.CouponIssueDto;
import com.smile.prebookingforgames.entity.CouponEntity;
import com.smile.prebookingforgames.error.ServiceError;
import com.smile.prebookingforgames.exception.PreBookingException;
import com.smile.prebookingforgames.repository.CouponRepository;

import java.util.Random;

public class AlphabetAndNumericCoupon extends Coupon {

    public AlphabetAndNumericCoupon(CouponRepository couponRepository) {
        super(couponRepository);
    }

    @Override
    public CouponEntity issue(CouponIssueDto.Request couponIssueDto) {
        if(isDuplicatePhoneNumber(couponIssueDto.getPhoneNumber())) throw new PreBookingException(ServiceError.PHONE_NUMBER_DUPLICATE);
        Random rnd =new Random();
        StringBuffer buf =new StringBuffer();
        int i = 0;
        boolean check = true;
        while (check){
            if(rnd.nextBoolean()){
                if(rnd.nextBoolean()){
                    buf.append((char)((int)(rnd.nextInt(26))+97)); //랜덤 소문자
                }else {
                    buf.append((char)((int)(rnd.nextInt(26))+65)); //랜덤 대문자
                }

            }else{
                buf.append((rnd.nextInt(10)));
            }

            if(i == 3 || i==7){
                buf.append("-");
            }
            i++;

            if(i==12){
                if(isDuplicateCoupon(buf.toString())){
                    i=0;
                }else{
                    check = false;
                }
            }
        }
        return saveEntity(couponIssueDto, buf.toString());
    }
}
