package com.mertosi.deliverymanagement.model.dto.response.delivery;

import com.mertosi.deliverymanagement.model.TestDataBuilder;

public class DeliveryResponseBuilder extends TestDataBuilder<DeliveryResponse> {

    public DeliveryResponseBuilder() {
        super(DeliveryResponse.class);
    }

    public static DeliveryResponse getValidDeliveryResponse() {
        return new DeliveryResponseBuilder().build();
    }

    public DeliveryResponseBuilder withBarcode(String barcode) {
        data.setBarcode(barcode);
        return this;
    }
}
