package com.mertosi.delivery.common.mapper.shipment;

import com.mertosi.delivery.common.mapper.BaseMapper;
import com.mertosi.delivery.model.dto.response.ShipmentResponse;
import com.mertosi.delivery.model.entity.ShipmentEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ShipmentResponseMapper extends BaseMapper<ShipmentEntity, ShipmentResponse> {
}
