package com.mertosi.deliverymanagement.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SuperBuilder
public class BaseResponse<T> {
    public static final BaseResponse<Object> SUCCESS_RESPONSE = BaseResponse.builder().success(true).build();

    @NotNull
    @Builder.Default
    private boolean success = true;

    private String errorMessage;

    private T data;
}
