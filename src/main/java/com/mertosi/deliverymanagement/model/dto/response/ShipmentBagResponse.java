package com.mertosi.deliverymanagement.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShipmentBagResponse extends AbstractResponse {
    private ShipmentResponse barcode;
    private BagResponse bagBarcode;
}
