package com.mertosi.delivery.model.dto.response;

import com.mertosi.delivery.common.enums.ShipmentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShipmentResponse extends AbstractResponse {
    private String barcode;
    private DeliveryPointResponse deliveryPoint;
    private Integer volumetricWeight;
    private ShipmentStatus status;
}
