package com.mertosi.delivery.service.bag;

import com.mertosi.delivery.common.enums.BagStatus;
import com.mertosi.delivery.model.dto.request.BagRequest;
import com.mertosi.delivery.model.entity.BagEntity;

public interface BagCommandService {
    BagEntity create(BagRequest request);

    BagEntity updateStatus(BagEntity bagEntity, BagStatus status);
}
