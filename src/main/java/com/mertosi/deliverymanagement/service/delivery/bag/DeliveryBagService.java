package com.mertosi.deliverymanagement.service.delivery.bag;

import com.mertosi.deliverymanagement.model.dto.response.delivery.DeliveryResponse;
import com.mertosi.deliverymanagement.model.dto.response.delivery.RouteResponse;
import com.mertosi.deliverymanagement.model.entity.BagEntity;

public interface DeliveryBagService {
    void delivery(RouteResponse routeResponse, DeliveryResponse deliveryResponse);

    boolean isLoadable(BagEntity bagEntity, Integer routeDeliveryPoint);

    void checkBagsStatusAfterDelivery();
}
