package com.mertosi.deliverymanagement.model.dto.response.delivery;

import com.mertosi.deliverymanagement.model.TestDataBuilder;

public class MakeDeliveryResponseBuilder extends TestDataBuilder<MakeDeliveryResponse> {

    public MakeDeliveryResponseBuilder() {
        super(MakeDeliveryResponse.class);
    }

    public static MakeDeliveryResponse getValidMakeDeliveryResponse() {
        return new MakeDeliveryResponseBuilder().build();
    }
}
