package com.mertosi.deliverymanagement.service.vehicle;

import com.mertosi.deliverymanagement.common.mapper.vehicle.VehicleRequestMapper;
import com.mertosi.deliverymanagement.model.dto.request.VehicleRequest;
import com.mertosi.deliverymanagement.model.entity.VehicleEntity;
import com.mertosi.deliverymanagement.repository.vehicle.VehicleCommandRepository;
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
