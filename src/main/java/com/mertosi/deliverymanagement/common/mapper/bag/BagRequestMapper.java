package com.mertosi.deliverymanagement.common.mapper.bag;

import com.mertosi.deliverymanagement.common.mapper.BaseMapper;
import com.mertosi.deliverymanagement.model.dto.request.BagRequest;
import com.mertosi.deliverymanagement.model.entity.BagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BagRequestMapper extends BaseMapper<BagRequest, BagEntity> {

    @Override
    @Mapping(target = "deliveryPoint", ignore = true)
    BagEntity map(BagRequest source);
}
