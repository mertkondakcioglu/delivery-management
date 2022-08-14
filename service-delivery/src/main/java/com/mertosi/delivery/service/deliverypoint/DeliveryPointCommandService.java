package com.mertosi.delivery.service.deliverypoint;

import com.mertosi.delivery.model.dto.request.DeliveryPointRequest;
import com.mertosi.delivery.model.entity.DeliveryPointEntity;

public interface DeliveryPointCommandService {
    DeliveryPointEntity create(DeliveryPointRequest request);
}
