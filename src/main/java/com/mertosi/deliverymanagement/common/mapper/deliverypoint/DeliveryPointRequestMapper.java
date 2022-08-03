package com.mertosi.deliverymanagement.common.mapper.deliverypoint;

import com.mertosi.deliverymanagement.common.mapper.BaseMapper;
import com.mertosi.deliverymanagement.model.dto.request.DeliveryPointRequest;
import com.mertosi.deliverymanagement.model.entity.DeliveryPointEntity;
import org.mapstruct.Mapper;

@Mapper
public interface DeliveryPointRequestMapper extends BaseMapper<DeliveryPointRequest, DeliveryPointEntity> {
}
