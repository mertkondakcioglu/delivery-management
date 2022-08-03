package com.mertosi.deliverymanagement.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class DeliveryPointRequest {
    @NotBlank
    private String deliveryPoint;

    @NotNull
    @Positive
    private Integer value;
}
