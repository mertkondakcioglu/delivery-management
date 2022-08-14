package com.mertosi.delivery.common.mapper.delivery;

import com.mertosi.delivery.common.mapper.BaseMapper;
import com.mertosi.delivery.model.dto.request.delivery.MakeDeliveryRequest;
import com.mertosi.delivery.model.dto.response.delivery.MakeDeliveryResponse;
import org.mapstruct.Mapper;

@Mapper
public interface DeliveryResponseMapper extends BaseMapper<MakeDeliveryRequest, MakeDeliveryResponse> {
}
