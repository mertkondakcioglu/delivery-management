package com.mertosi.deliverymanagement.model.dto.request;

import com.mertosi.deliverymanagement.model.TestDataBuilder;

import java.util.ArrayList;
import java.util.List;

public class ShipmentBagRequestBuilder extends TestDataBuilder<ShipmentBagRequest> {

    public ShipmentBagRequestBuilder() {
        super(ShipmentBagRequest.class);
    }

    public static List<ShipmentBagRequest> getValidShipmentBagRequests() {
        List<ShipmentBagRequest> shipmentBagEntities = new ArrayList<>();
        shipmentBagEntities.add(new ShipmentBagRequestBuilder().withBarcode("P7988000121").withBagBarcode("C725799").build());
        shipmentBagEntities.add(new ShipmentBagRequestBuilder().withBarcode("P7988000122").withBagBarcode("C725799").build());
        return shipmentBagEntities;
    }

    public ShipmentBagRequestBuilder withBarcode(String barcode) {
        data.setBarcode(barcode);
        return this;
    }

    public ShipmentBagRequestBuilder withBagBarcode(String bagBarcode) {
        data.setBagBarcode(bagBarcode);
        return this;
    }
}
