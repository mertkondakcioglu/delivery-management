package com.mertosi.deliverymanagement.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
public class BagRequest {
    @NotBlank
    @Size(min = 7, max = 7)
    private String barcode;

    @NotNull
    @Positive
    private Integer deliveryPoint;
}
