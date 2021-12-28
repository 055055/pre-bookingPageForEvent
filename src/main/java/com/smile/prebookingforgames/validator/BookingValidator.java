package com.smile.prebookingforgames.validator;

import com.smile.prebookingforgames.dto.CouponIssueDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;


@Component
public class BookingValidator {
    public void validate(CouponIssueDto couponIssueDto, Errors errors){

        String regPhone = "^((01[1|6|7|8|9])[1-9]+[0-9]{6,7})|(010[1-9][0-9]{7})$";
      String phoneNumber =  couponIssueDto.getPhoneNumber();
        if(!phoneNumber.matches(regPhone)){
            errors.reject("phoneNumber check!");
        }


        if(couponIssueDto.isPrivateYn() != true){
            errors.reject("privateYn check!");
        }
    }

}
