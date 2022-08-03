package com.mertosi.deliverymanagement.model.dto.response.delivery;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryResponse {
    private String barcode;
    private Integer state;
}
