package com.mertosi.deliverymanagement.service.vehicle;

import com.mertosi.deliverymanagement.model.dto.request.VehicleRequest;
import com.mertosi.deliverymanagement.model.entity.VehicleEntity;

public interface VehicleCommandService {
    VehicleEntity create(VehicleRequest request);
}
