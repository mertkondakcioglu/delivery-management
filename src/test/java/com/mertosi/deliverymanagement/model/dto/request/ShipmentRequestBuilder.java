package com.mertosi.deliverymanagement.model.dto.request;

import com.mertosi.deliverymanagement.model.TestDataBuilder;

import java.util.ArrayList;
import java.util.List;

public class ShipmentRequestBuilder extends TestDataBuilder<ShipmentRequest> {

    public ShipmentRequestBuilder() {
        super(ShipmentRequest.class);
    }

    public static List<ShipmentRequest> getValidShipmentRequests() {
        List<ShipmentRequest> shipmentRequests = new ArrayList<>();
        shipmentRequests.add(new ShipmentRequestBuilder().withBarcode("P7988000121").build());
        shipmentRequests.add(new ShipmentRequestBuilder().withBarcode("P7988000122").build());
        return shipmentRequests;
    }

    public ShipmentRequestBuilder withBarcode(String barcode) {
        data.setBarcode(barcode);
        return this;
    }
}
