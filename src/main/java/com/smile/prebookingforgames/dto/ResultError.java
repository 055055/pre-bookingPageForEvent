package com.smile.prebookingforgames.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@ToString
@Getter
@Setter
@Builder
public class ResultError {
    @JsonProperty("resultCode")
    private String resultCode;
    @JsonProperty("resultMessage")
    private String resultMessage;
    @JsonProperty("httpStatus")
    private HttpStatus httpStatus;


}
