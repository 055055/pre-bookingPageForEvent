package com.smile.prebookingforgames.validator;

import com.smile.prebookingforgames.dto.RegisterReqDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;


@Component
public class BookingValidator {
    public void validate(RegisterReqDTO registerReqDTO, Errors errors){

        String regPhone = "^((01[1|6|7|8|9])[1-9]+[0-9]{6,7})|(010[1-9][0-9]{7})$";
      String phoneNumber =  registerReqDTO.getPhoneNumber();
        if(!phoneNumber.matches(regPhone)){
            errors.reject("phoneNumber check!");
        }


        if(registerReqDTO.isPrivateYn() != true){
            errors.reject("privateYn check!");
        }
    }

}
