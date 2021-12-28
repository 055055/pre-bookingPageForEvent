package com.smile.prebookingforgames.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CouponIssuedListDto {
    private Long couponSeq;
    private String phoneNumber;
    private boolean privateYn;
    private String couponNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime regDate;

    public String getPrivateYn() {
        return this.privateYn ? "Y" : "N";
    }
}