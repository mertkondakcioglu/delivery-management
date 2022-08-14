package com.mertosi.delivery.model.dto.request;

import com.mertosi.delivery.model.TestDataBuilder;

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
