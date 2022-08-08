package com.mertosi.deliverymanagement.service.shipment;

import com.mertosi.deliverymanagement.common.enums.ShipmentStatus;
import com.mertosi.deliverymanagement.model.dto.request.ShipmentRequest;
import com.mertosi.deliverymanagement.model.entity.ShipmentEntity;

import java.util.List;

public interface ShipmentCommandService {
    List<ShipmentEntity> create(List<ShipmentRequest> requests);

    ShipmentEntity updateStatus(ShipmentEntity shipmentEntity, ShipmentStatus status);
}
