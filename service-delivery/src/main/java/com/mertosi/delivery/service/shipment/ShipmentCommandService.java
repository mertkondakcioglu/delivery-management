package com.mertosi.delivery.service.shipment;

import com.mertosi.delivery.common.enums.ShipmentStatus;
import com.mertosi.delivery.model.dto.request.ShipmentRequest;
import com.mertosi.delivery.model.entity.ShipmentEntity;

import java.util.List;

public interface ShipmentCommandService {
    List<ShipmentEntity> create(List<ShipmentRequest> requests);

    ShipmentEntity updateStatus(ShipmentEntity shipmentEntity, ShipmentStatus status);
}
