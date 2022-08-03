package com.mertosi.deliverymanagement.service.deliverypoint;

import com.mertosi.deliverymanagement.model.entity.DeliveryPointEntity;

public interface DeliveryPointQueryService {
    DeliveryPointEntity getByValue(Integer id);
}
