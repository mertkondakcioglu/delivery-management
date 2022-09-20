package com.mertosi.delivery.service.delivery;

import com.mertosi.delivery.common.enums.DeliveryType;
import com.mertosi.delivery.model.dto.response.delivery.DeliveryResponse;
import com.mertosi.delivery.model.dto.response.delivery.RouteResponse;

public interface Delivery {
    void delivery(RouteResponse routeResponse, DeliveryResponse deliveryResponse);

    DeliveryType type();
}
