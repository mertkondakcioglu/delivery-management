package com.mertosi.delivery.common.mapper.bag;

import com.mertosi.delivery.common.mapper.BaseMapper;
import com.mertosi.delivery.model.dto.request.BagRequest;
import com.mertosi.delivery.model.entity.BagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BagRequestMapper extends BaseMapper<BagRequest, BagEntity> {

    @Override
    @Mapping(target = "deliveryPoint", ignore = true)
    BagEntity map(BagRequest source);
}
