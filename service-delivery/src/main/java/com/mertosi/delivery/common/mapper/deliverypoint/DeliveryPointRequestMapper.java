package com.mertosi.delivery.common.mapper.deliverypoint;

import com.mertosi.delivery.common.mapper.BaseMapper;
import com.mertosi.delivery.model.dto.request.DeliveryPointRequest;
import com.mertosi.delivery.model.entity.DeliveryPointEntity;
import org.mapstruct.Mapper;

@Mapper
public interface DeliveryPointRequestMapper extends BaseMapper<DeliveryPointRequest, DeliveryPointEntity> {
}
