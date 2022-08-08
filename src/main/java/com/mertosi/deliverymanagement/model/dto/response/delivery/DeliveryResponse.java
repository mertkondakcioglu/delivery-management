package com.mertosi.deliverymanagement.model.dto.response.delivery;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
public class DeliveryResponse {
    private String barcode;
    private Integer state;

    public boolean isDeliveryShipment() {
        return StringUtils.hasText(barcode) && barcode.startsWith("P") && barcode.length() == 11;
    }

    public boolean isDeliveryBag() {
        return StringUtils.hasText(barcode) && barcode.startsWith("C") && barcode.length() == 7;
    }
}
