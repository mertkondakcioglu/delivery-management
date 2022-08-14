package com.mertosi.vehicle.common.mapper;

import com.mertosi.vehicle.model.dto.response.VehicleResponse;
import com.mertosi.vehicle.model.entity.VehicleEntity;
import org.mapstruct.Mapper;

@Mapper
public interface VehicleResponseMapper extends BaseMapper<VehicleEntity, VehicleResponse> {
}
