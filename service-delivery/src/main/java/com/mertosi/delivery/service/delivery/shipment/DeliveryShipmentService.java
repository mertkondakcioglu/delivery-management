package com.mertosi.delivery.service.delivery.shipment;

import com.mertosi.delivery.model.dto.response.delivery.DeliveryResponse;
import com.mertosi.delivery.model.dto.response.delivery.RouteResponse;
import com.mertosi.delivery.model.entity.ShipmentEntity;

public interface DeliveryShipmentService {
    void delivery(RouteResponse routeResponse, DeliveryResponse deliveryResponse);

    boolean isLoadable(ShipmentEntity entity, Integer routeDeliveryPoint);
}
