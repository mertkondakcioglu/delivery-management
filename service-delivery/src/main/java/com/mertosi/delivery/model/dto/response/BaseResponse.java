package com.mertosi.delivery.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class BaseResponse<T> {

    @NotNull
    @Builder.Default
    private boolean success = true;

    private String errorMessage;

    private T data;
}
