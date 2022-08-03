package com.mertosi.deliverymanagement.common.mapper.shipment;

import com.mertosi.deliverymanagement.common.mapper.BaseMapper;
import com.mertosi.deliverymanagement.model.dto.response.ShipmentResponse;
import com.mertosi.deliverymanagement.model.entity.ShipmentEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ShipmentResponseMapper extends BaseMapper<ShipmentEntity, ShipmentResponse> {
}
