package com.mertosi.delivery.common.mapper.shipment;

import com.mertosi.delivery.common.mapper.BaseMapper;
import com.mertosi.delivery.model.dto.request.ShipmentRequest;
import com.mertosi.delivery.model.entity.ShipmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ShipmentRequestMapper extends BaseMapper<ShipmentRequest, ShipmentEntity> {

    @Override
    @Mapping(target = "deliveryPoint", ignore = true)
    ShipmentEntity map(ShipmentRequest source);
}
