package com.mertosi.deliverymanagement.common.mapper.vehicle;

import com.mertosi.deliverymanagement.common.mapper.BaseMapper;
import com.mertosi.deliverymanagement.model.dto.response.VehicleResponse;
import com.mertosi.deliverymanagement.model.entity.VehicleEntity;
import org.mapstruct.Mapper;

@Mapper
public interface VehicleResponseMapper extends BaseMapper<VehicleEntity, VehicleResponse> {
}
