package com.mertosi.deliverymanagement.model.dto.response;

import com.mertosi.deliverymanagement.common.enums.PackageStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShipmentResponse extends AbstractResponse {
    private String barcode;
    private DeliveryPointResponse deliveryPoint;
    private Integer volumetricWeight;
    private PackageStatus status;
}
