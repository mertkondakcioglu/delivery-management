package com.mertosi.deliverymanagement.service.deliverypoint;

import com.mertosi.deliverymanagement.model.dto.request.DeliveryPointRequest;
import com.mertosi.deliverymanagement.model.entity.DeliveryPointEntity;

public interface DeliveryPointCommandService {
    DeliveryPointEntity create(DeliveryPointRequest request);
}
