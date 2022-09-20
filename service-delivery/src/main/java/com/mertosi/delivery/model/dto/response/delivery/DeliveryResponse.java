package com.mertosi.delivery.model.dto.response.delivery;

import com.mertosi.delivery.common.enums.DeliveryType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@Setter
public class DeliveryResponse {
    private String barcode;
    private Integer state;

    public DeliveryType getType() {
        if (isDeliveryBag()) {
            return DeliveryType.BAG;
        }
        return DeliveryType.SHIPMENT;
    }

    public boolean isDeliveryBag() {
        return StringUtils.hasText(barcode) && barcode.startsWith("C") && barcode.length() == 7;
    }
}
