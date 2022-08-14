package com.mertosi.vehicle.service;

import com.mertosi.vehicle.model.dto.request.VehicleRequest;
import com.mertosi.vehicle.model.entity.VehicleEntity;

public interface VehicleCommandService {
    VehicleEntity create(VehicleRequest request);
}
