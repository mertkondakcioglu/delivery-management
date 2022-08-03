package com.mertosi.deliverymanagement.service.vehicle;

import com.mertosi.deliverymanagement.common.exception.NotFoundException;
import com.mertosi.deliverymanagement.model.entity.VehicleEntity;
import com.mertosi.deliverymanagement.repository.vehicle.VehicleQueryRepository;
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
