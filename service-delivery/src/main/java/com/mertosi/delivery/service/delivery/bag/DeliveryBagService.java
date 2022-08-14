package com.mertosi.delivery.service.delivery.bag;

import com.mertosi.delivery.model.dto.response.delivery.DeliveryResponse;
import com.mertosi.delivery.model.dto.response.delivery.RouteResponse;
import com.mertosi.delivery.model.entity.BagEntity;

public interface DeliveryBagService {
    void delivery(RouteResponse routeResponse, DeliveryResponse deliveryResponse);

    boolean isLoadable(BagEntity bagEntity, Integer routeDeliveryPoint);

    void checkBagsStatusAfterDelivery();
}
