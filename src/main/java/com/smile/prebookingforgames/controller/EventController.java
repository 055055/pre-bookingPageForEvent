package com.smile.prebookingforgames.controller;

import com.smile.prebookingforgames.dto.CouponIssueDto;
import com.smile.prebookingforgames.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@Slf4j
@RequestMapping("/api/events")
@RestController
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity issueCoupon(@RequestBody @Valid CouponIssueDto.Request couponIssueDto) {
        return ResponseEntity.ok().body(eventService.issueCoupon(couponIssueDto));
    }

    @GetMapping(value = "/coupon-list/all")
    public Map<String, List> findAllCoupon() {
        return Collections.singletonMap("data", eventService.findAllCoupon());
    }
}