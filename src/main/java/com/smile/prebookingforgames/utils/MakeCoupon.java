package com.smile.prebookingforgames.utils;

import com.smile.prebookingforgames.error.ServiceError;
import com.smile.prebookingforgames.exception.PreBookingException;
import com.smile.prebookingforgames.repository.CouponRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Random;

@Component
public class MakeCoupon {
    private CouponRepository couponRepository;
    public MakeCoupon(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public String digit12Number() throws PreBookingException {
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
                Optional<String> found =  couponRepository.findByCouponNumber(buf.toString());
                if (found.isPresent()){
                    i=0;
                }else{
                    check = false;
                }
            }

        }
        return buf.toString();
    }


    public void phoneNumberDuplicateCheck(String phoneNumber) throws PreBookingException {
      Optional<String> found =  couponRepository.findByPhoneNumber(phoneNumber);
        if(found.isPresent()){
            throw new PreBookingException(ServiceError.PHONE_NUMBER_DUPLICATE);
        }

    }
}
