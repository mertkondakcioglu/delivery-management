package com.mertosi.deliverymanagement.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ShipmentBagRequest {
    @NotBlank
    @Size(min = 11, max = 11)
    private String barcode;

    @NotBlank
    @Size(min = 7, max = 7)
    private String bagBarcode;
}
