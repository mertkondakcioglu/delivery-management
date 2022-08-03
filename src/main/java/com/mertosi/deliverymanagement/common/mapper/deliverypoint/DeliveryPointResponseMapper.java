package com.mertosi.deliverymanagement.common.mapper.deliverypoint;

import com.mertosi.deliverymanagement.common.mapper.BaseMapper;
import com.mertosi.deliverymanagement.model.dto.response.DeliveryPointResponse;
import com.mertosi.deliverymanagement.model.entity.DeliveryPointEntity;
import org.mapstruct.Mapper;

@Mapper
public interface DeliveryPointResponseMapper extends BaseMapper<DeliveryPointEntity, DeliveryPointResponse> {
}
