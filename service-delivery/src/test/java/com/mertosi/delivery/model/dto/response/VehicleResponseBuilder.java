package com.mertosi.delivery.model.dto.response;

import com.mertosi.delivery.model.TestDataBuilder;

public class VehicleResponseBuilder extends TestDataBuilder<VehicleResponse> {

    public VehicleResponseBuilder() {
        super(VehicleResponse.class);
    }

    public static VehicleResponse getValidVehicleResponse() {
        return new VehicleResponseBuilder().build();
    }
}
