package com.mertosi.vehicle.service;

import com.mertosi.vehicle.common.exception.NotFoundException;
import com.mertosi.vehicle.model.entity.VehicleEntity;
import com.mertosi.vehicle.repository.VehicleQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleQueryServiceImpl implements VehicleQueryService {

    private final VehicleQueryRepository vehicleQueryRepository;

    @Override
    public VehicleEntity getByLicensePlate(String licensePlate) {
        return vehicleQueryRepository.findByLicensePlate(licensePlate)
                .orElseThrow(() -> new NotFoundException(licensePlate));
    }
}
