package com.mertosi.deliverymanagement.service.bag;

import com.mertosi.deliverymanagement.common.enums.BagStatus;
import com.mertosi.deliverymanagement.model.dto.request.BagRequest;
import com.mertosi.deliverymanagement.model.entity.BagEntity;

public interface BagCommandService {
    BagEntity create(BagRequest request);

    BagEntity updateStatus(BagEntity bagEntity, BagStatus status);
}
