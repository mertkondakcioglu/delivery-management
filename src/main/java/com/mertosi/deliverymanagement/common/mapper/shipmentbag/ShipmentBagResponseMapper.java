package com.mertosi.deliverymanagement.common.mapper.shipmentbag;

import com.mertosi.deliverymanagement.common.mapper.BaseMapper;
import com.mertosi.deliverymanagement.model.dto.response.ShipmentBagResponse;
import com.mertosi.deliverymanagement.model.entity.ShipmentBagEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ShipmentBagResponseMapper extends BaseMapper<ShipmentBagEntity, ShipmentBagResponse> {
}
