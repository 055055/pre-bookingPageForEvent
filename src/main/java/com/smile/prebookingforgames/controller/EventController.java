package com.smile.prebookingforgames.controller;

import com.smile.prebookingforgames.dto.CouponListDTO;
import com.smile.prebookingforgames.dto.CouponIssueDto;
import com.smile.prebookingforgames.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RequestMapping("/api/events")
@Controller
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping
    public String forwardCouponIssuePage() {
        return "register";
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity issueCoupon(@RequestBody @Valid CouponIssueDto couponIssueDto) {
        Map<String, String> result = eventService.registerCoupon(couponIssueDto);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/coupon-list")
    public String forwardCouponListPage() {
        return "coupon-list";
    }

    @GetMapping(value = "/coupon-list/all")
    public @ResponseBody
    Map<String, List> couponListAll() {
        List<CouponListDTO> result = eventService.getCouponList();
        Map<String, List> data = new HashMap<>();
        data.put("data", result);
        return data;
    }
}