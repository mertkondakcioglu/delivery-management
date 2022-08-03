package com.mertosi.deliverymanagement.service.shipmentbag;

import com.mertosi.deliverymanagement.model.dto.request.ShipmentBagRequest;
import com.mertosi.deliverymanagement.model.entity.ShipmentBagEntity;

import java.util.List;

public interface ShipmentBagCommandService {
    List<ShipmentBagEntity> create(List<ShipmentBagRequest> requests);
}
