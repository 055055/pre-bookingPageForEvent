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
    @NotEmpty
    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @NotNull
    @JsonProperty("privateYn")
    private boolean privateYn;

}
