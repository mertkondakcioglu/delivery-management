package com.mertosi.delivery.service.delivery.shipment;

import com.mertosi.delivery.model.entity.ShipmentEntity;
import com.mertosi.delivery.service.delivery.Delivery;

public interface DeliveryShipmentService extends Delivery {
    boolean isLoadable(ShipmentEntity entity, Integer routeDeliveryPoint);
}
