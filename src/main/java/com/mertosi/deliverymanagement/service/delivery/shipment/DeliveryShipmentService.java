package com.mertosi.deliverymanagement.service.delivery.shipment;

import com.mertosi.deliverymanagement.model.dto.response.delivery.DeliveryResponse;
import com.mertosi.deliverymanagement.model.dto.response.delivery.RouteResponse;
import com.mertosi.deliverymanagement.model.entity.ShipmentEntity;

public interface DeliveryShipmentService {
    void delivery(RouteResponse routeResponse, DeliveryResponse deliveryResponse);

    boolean isLoadable(ShipmentEntity entity, Integer routeDeliveryPoint);
}
