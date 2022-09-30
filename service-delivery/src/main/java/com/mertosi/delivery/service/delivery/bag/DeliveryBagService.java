package com.mertosi.delivery.service.delivery.bag;

import com.mertosi.delivery.model.entity.BagEntity;
import com.mertosi.delivery.service.delivery.BaseDelivery;

public interface DeliveryBagService extends BaseDelivery {
    boolean isLoadable(BagEntity bagEntity, Integer routeDeliveryPoint);

    void checkBagsStatusAfterDelivery();
}
