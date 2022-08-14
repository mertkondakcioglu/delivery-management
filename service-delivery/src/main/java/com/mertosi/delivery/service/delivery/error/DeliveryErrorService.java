package com.mertosi.delivery.service.delivery.error;

import com.mertosi.delivery.model.entity.DeliveryErrorEntity;

public interface DeliveryErrorService {
    DeliveryErrorEntity create(String barcode, Integer deliveryPoint);
}
