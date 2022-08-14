package com.mertosi.delivery.model.dto.response;

import com.mertosi.delivery.common.enums.BagStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BagResponse extends AbstractResponse {
    private String barcode;
    private DeliveryPointResponse deliveryPoint;
    private BagStatus status;
}
