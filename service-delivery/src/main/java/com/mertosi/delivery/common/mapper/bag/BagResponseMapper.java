package com.mertosi.delivery.common.mapper.bag;

import com.mertosi.delivery.common.mapper.BaseMapper;
import com.mertosi.delivery.model.dto.response.BagResponse;
import com.mertosi.delivery.model.entity.BagEntity;
import org.mapstruct.Mapper;

@Mapper
public interface BagResponseMapper extends BaseMapper<BagEntity, BagResponse> {
}
