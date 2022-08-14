package com.mertosi.vehicle.service;

import com.mertosi.vehicle.common.mapper.VehicleRequestMapper;
import com.mertosi.vehicle.model.dto.request.VehicleRequest;
import com.mertosi.vehicle.model.entity.VehicleEntity;
import com.mertosi.vehicle.repository.VehicleCommandRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleCommandServiceImpl implements VehicleCommandService {

    private final VehicleCommandRepository vehicleCommandRepository;

    private final VehicleRequestMapper mapper = Mappers.getMapper(VehicleRequestMapper.class);

    @Override
    public VehicleEntity create(VehicleRequest request) {
        VehicleEntity vehicleEntity = mapper.map(request);
        return vehicleCommandRepository.save(vehicleEntity);
    }
}
