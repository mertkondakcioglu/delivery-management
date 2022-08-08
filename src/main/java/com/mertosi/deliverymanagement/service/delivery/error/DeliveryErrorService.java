package com.mertosi.deliverymanagement.service.delivery.error;

import com.mertosi.deliverymanagement.model.entity.DeliveryErrorEntity;

public interface DeliveryErrorService {
    DeliveryErrorEntity create(String barcode, Integer deliveryPoint);
}
