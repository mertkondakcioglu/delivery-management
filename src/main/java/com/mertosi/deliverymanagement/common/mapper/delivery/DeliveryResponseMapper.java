package com.mertosi.deliverymanagement.common.mapper.delivery;

import com.mertosi.deliverymanagement.common.mapper.BaseMapper;
import com.mertosi.deliverymanagement.model.dto.request.delivery.MakeDeliveryRequest;
import com.mertosi.deliverymanagement.model.dto.response.delivery.MakeDeliveryResponse;
import org.mapstruct.Mapper;

@Mapper
public interface DeliveryResponseMapper extends BaseMapper<MakeDeliveryRequest, MakeDeliveryResponse> {
}
