package com.smile.prebookingforgames.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class CouponIssueDto {
    @Pattern(regexp = "^((01[1|6|7|8|9])[1-9]+[0-9]{6,7})|(010[1-9][0-9]{7})$", message = "휴대폰 번호를 확인해주세요.")
    @NotBlank(message = "휴대폰 번호를 확인해 주세요.")
    private String phoneNumber;

    @AssertTrue(message = "개인정보 수집 이용 및 SMS 수신에 동의해야 합니다.")
    private boolean privateYn;
}