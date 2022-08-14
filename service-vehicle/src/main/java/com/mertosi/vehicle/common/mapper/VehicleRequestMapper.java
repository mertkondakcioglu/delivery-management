package com.mertosi.vehicle.common.mapper;

import com.mertosi.vehicle.model.dto.request.VehicleRequest;
import com.mertosi.vehicle.model.entity.VehicleEntity;
import org.mapstruct.Mapper;

@Mapper
public interface VehicleRequestMapper extends BaseMapper<VehicleRequest, VehicleEntity> {
}
