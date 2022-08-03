package com.mertosi.deliverymanagement.model.dto.request;

import com.mertosi.deliverymanagement.model.TestDataBuilder;

public class VehicleRequestBuilder extends TestDataBuilder<VehicleRequest> {

    public VehicleRequestBuilder() {
        super(VehicleRequest.class);
    }

    public static VehicleRequest getValidVehicleRequest() {
        return new VehicleRequestBuilder().build();
    }
}
