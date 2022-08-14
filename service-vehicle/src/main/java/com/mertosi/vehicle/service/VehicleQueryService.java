package com.mertosi.vehicle.service;

import com.mertosi.vehicle.model.entity.VehicleEntity;

public interface VehicleQueryService {
    VehicleEntity getByLicensePlate(String licensePlate);
}
