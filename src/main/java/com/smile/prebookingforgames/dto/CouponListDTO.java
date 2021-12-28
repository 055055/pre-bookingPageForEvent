package com.smile.prebookingforgames.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
public class CouponListDTO {
    @JsonProperty("couponSeq")
    private Long couponSeq;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("privateYn")
    private boolean privateYn;

    @JsonProperty("couponNumber")
    private String couponNumber;

    @JsonProperty("regDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime regDate;

    public String getPrivateYn() {
        if(this.privateYn == true){
            return "Y";
        }else{
            return "N";
        }
    }
}