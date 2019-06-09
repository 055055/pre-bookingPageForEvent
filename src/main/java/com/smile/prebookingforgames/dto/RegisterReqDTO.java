package com.smile.prebookingforgames.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class RegisterReqDTO {
    @NotEmpty @NotNull
    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @NotEmpty @NotNull
    @JsonProperty("privateYn")
    private String privateYn;

    public boolean getprivateYn(){
        if(this.privateYn.equalsIgnoreCase("on")){
            return true;
        }else {
            return false;
        }

    }

}
