package com.mertosi.deliverymanagement.model.dto.request;

import com.mertosi.deliverymanagement.model.TestDataBuilder;

public class DeliveryPointRequestBuilder extends TestDataBuilder<DeliveryPointRequest> {

    public DeliveryPointRequestBuilder() {
        super(DeliveryPointRequest.class);
    }

    public static DeliveryPointRequest getValidDeliveryPointRequest() {
        return new DeliveryPointRequestBuilder().build();
    }
}
