package com.mertosi.delivery.service.deliverypoint;

import com.mertosi.delivery.model.entity.DeliveryPointEntity;

public interface DeliveryPointQueryService {
    DeliveryPointEntity getByValue(Integer id);
}
