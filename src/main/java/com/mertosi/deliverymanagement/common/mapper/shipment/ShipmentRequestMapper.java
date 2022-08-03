package com.mertosi.deliverymanagement.common.mapper.shipment;

import com.mertosi.deliverymanagement.common.mapper.BaseMapper;
import com.mertosi.deliverymanagement.model.dto.request.ShipmentRequest;
import com.mertosi.deliverymanagement.model.entity.ShipmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ShipmentRequestMapper extends BaseMapper<ShipmentRequest, ShipmentEntity> {

    @Override
    @Mapping(target = "deliveryPoint", ignore = true)
    ShipmentEntity map(ShipmentRequest source);
}
