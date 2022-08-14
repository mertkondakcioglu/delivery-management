package com.mertosi.delivery.common.mapper.shipmentbag;

import com.mertosi.delivery.common.mapper.BaseMapper;
import com.mertosi.delivery.model.dto.response.ShipmentBagResponse;
import com.mertosi.delivery.model.entity.ShipmentBagEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ShipmentBagResponseMapper extends BaseMapper<ShipmentBagEntity, ShipmentBagResponse> {
}
