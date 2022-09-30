package com.mertosi.delivery.service.delivery.shipment;

import com.mertosi.delivery.model.entity.ShipmentEntity;
import com.mertosi.delivery.service.delivery.BaseDelivery;

public interface DeliveryShipmentService extends BaseDelivery {
    boolean isLoadable(ShipmentEntity entity, Integer routeDeliveryPoint);
}
