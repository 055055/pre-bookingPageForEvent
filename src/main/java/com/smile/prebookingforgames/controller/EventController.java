package com.smile.prebookingforgames.controller;

import com.smile.prebookingforgames.dto.CouponListDTO;
import com.smile.prebookingforgames.dto.RegisterReqDTO;
import com.smile.prebookingforgames.error.ServiceError;
import com.smile.prebookingforgames.exception.PreBookingException;
import com.smile.prebookingforgames.service.EventService;
import com.smile.prebookingforgames.validator.BookingValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Controller
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final BookingValidator bookingValidator;

    @GetMapping(value = "/api/events")
    public String coupon() {
        return "register";
    }

    @PostMapping(value = "/api/events", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity coupon(@RequestBody @Valid RegisterReqDTO registerReqDTO, Errors errors) {
        if (errors.hasErrors()) {
            throw new PreBookingException(ServiceError.VALIDATION_CHECK_ERROR);
        }
        bookingValidator.validate(registerReqDTO, errors);
        if (errors.hasErrors()) {
            log.error("coupon POST error{}", errors.getGlobalError().getCodes());
            throw new PreBookingException(ServiceError.WRONG_PHONE_NUMBER);
        }

        log.info("RegisterReqDTO ; " + registerReqDTO.toString());
        Map<String, String> result = eventService.registerCoupon(registerReqDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/api/events/coupon-list")
    public String couponList() {
        return "coupon-list";
    }


    @GetMapping(value = "/api/events/coupon-list/all")
    public @ResponseBody
    Map<String, List> couponListAll() {
        List<CouponListDTO> result = eventService.getCouponList();
        Map<String, List> data = new HashMap<>();
        data.put("data", result);
        log.info("xxx data xxx " + result.toString());
        return data;
    }


}
