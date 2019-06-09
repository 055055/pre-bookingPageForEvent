package com.smile.prebookingforgames.controller;

import com.smile.prebookingforgames.dto.CouponListDTO;
import com.smile.prebookingforgames.dto.RegisterReqDTO;
import com.smile.prebookingforgames.error.ServiceError;
import com.smile.prebookingforgames.exception.PreBookingException;
import com.smile.prebookingforgames.service.BookingService;
import com.smile.prebookingforgames.validator.BookinValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    BookinValidator bookinValidator;


    @GetMapping(value = "/api/events")
    public String coupon(){
        return "register";
    }

    @PostMapping(value = "/api/events", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public  ResponseEntity coupon(@RequestBody @Valid  RegisterReqDTO registerReqDTO, Errors errors) throws PreBookingException {
        if(errors.hasErrors()){
            throw  new PreBookingException(ServiceError.VALIDATION_CHECK_ERROR);
        }
        bookinValidator.validate(registerReqDTO, errors);
        if(errors.hasErrors()){
            throw  new PreBookingException(ServiceError.WRONG_PHONE_NUMBER);
        }

        log.info("RegisterReqDTO ; "+registerReqDTO.toString());
         Map<String,String> result =   bookingService.registerCoupon(registerReqDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/api/events/coupon-list")
    public String couponList(){
        return "coupon-list";
    }


    @GetMapping(value = "/api/events/coupon-list/all")
    public @ResponseBody
    Map<String, List> couponListAll(){
        List<CouponListDTO> result = bookingService.getCouponList();
        Map<String, List> data = new HashMap<>();
        data.put("data",result);
        log.info("xxx data xxx "+result.toString());
        return data;
    }



}
