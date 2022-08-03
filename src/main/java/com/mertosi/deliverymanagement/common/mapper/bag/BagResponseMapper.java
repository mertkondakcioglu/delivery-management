package com.mertosi.deliverymanagement.common.mapper.bag;

import com.mertosi.deliverymanagement.common.mapper.BaseMapper;
import com.mertosi.deliverymanagement.model.dto.response.BagResponse;
import com.mertosi.deliverymanagement.model.entity.BagEntity;
import org.mapstruct.Mapper;

@Mapper
public interface BagResponseMapper extends BaseMapper<BagEntity, BagResponse> {
}
