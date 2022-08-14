package com.mertosi.delivery.service.shipmentbag;

import com.mertosi.delivery.model.dto.request.ShipmentBagRequest;
import com.mertosi.delivery.model.entity.ShipmentBagEntity;

import java.util.List;

public interface ShipmentBagCommandService {
    List<ShipmentBagEntity> create(List<ShipmentBagRequest> requests);
}
