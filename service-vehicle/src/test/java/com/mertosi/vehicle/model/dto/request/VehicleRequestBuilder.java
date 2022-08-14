package com.mertosi.vehicle.model.dto.request;

import com.mertosi.vehicle.model.TestDataBuilder;

public class VehicleRequestBuilder extends TestDataBuilder<VehicleRequest> {

    public VehicleRequestBuilder() {
        super(VehicleRequest.class);
    }

    public static VehicleRequest getValidVehicleRequest() {
        return new VehicleRequestBuilder().build();
    }
}
