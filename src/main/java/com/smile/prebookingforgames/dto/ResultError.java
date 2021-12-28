package com.smile.prebookingforgames.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ResultError {
    private String code;
    private String message;
    private List<FieldValue> fieldValues;

    @Getter
    @Builder
    public static class FieldValue {
        private String field;
        private Object value;
        private String reason;
    }
}