package com.mertosi.deliverymanagement.model.entity;

import com.mertosi.deliverymanagement.model.TestDataBuilder;

public class VehicleEntityBuilder extends TestDataBuilder<VehicleEntity> {

    public VehicleEntityBuilder() {
        super(VehicleEntity.class);
    }

    public VehicleEntityBuilder(boolean excludeRelations) {
        super(VehicleEntity.class, excludeRelations);
    }

    public static VehicleEntityBuilder getValidVehicleEntityWithoutRelations() {
        return new VehicleEntityBuilder(true);
    }

    public static VehicleEntity getValidVehicleEntity() {
        return new VehicleEntityBuilder().withLicensePlate("34 TR 34").build();
    }

    public VehicleEntityBuilder withLicensePlate(String licensePlate) {
        data.setLicensePlate(licensePlate);
        return this;
    }
}
