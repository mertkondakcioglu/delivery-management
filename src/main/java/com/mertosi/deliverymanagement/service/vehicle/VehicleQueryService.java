package com.mertosi.deliverymanagement.service.vehicle;

import com.mertosi.deliverymanagement.model.entity.VehicleEntity;

public interface VehicleQueryService {
    VehicleEntity getByLicensePlate(String licensePlate);
}
