package com.smile.prebookingforgames.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class CouponIssueDto {

    @Getter
    @Setter
    public static class Request extends CouponIssueDto {
        @Pattern(regexp = "^((01[1|6|7|8|9])[1-9]+[0-9]{6,7})|(010[1-9][0-9]{7})$", message = "휴대폰 번호를 확인해주세요.")
        @NotBlank(message = "휴대폰 번호를 확인해 주세요.")
        private String phoneNumber;

        @AssertTrue(message = "개인정보 수집 이용 및 SMS 수신에 동의해야 합니다.")
        private boolean privateYn;
    }

    @Getter
    @Builder
    public static class Response extends CouponIssueDto {
        private String couponNumber;
    }
}