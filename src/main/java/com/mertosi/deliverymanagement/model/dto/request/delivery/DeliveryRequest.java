package com.mertosi.deliverymanagement.model.dto.request.delivery;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class DeliveryRequest {
    @NotBlank
    @Size(min = 7, max = 11)
    private String barcode;
}
