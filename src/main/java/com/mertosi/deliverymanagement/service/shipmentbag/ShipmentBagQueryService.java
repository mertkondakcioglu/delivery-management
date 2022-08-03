package com.mertosi.deliverymanagement.service.shipmentbag;

import com.mertosi.deliverymanagement.model.entity.ShipmentBagEntity;
import com.mertosi.deliverymanagement.model.entity.ShipmentEntity;

import java.util.List;

public interface ShipmentBagQueryService {
    ShipmentBagEntity getByBarcode(ShipmentEntity barcode);

    List<ShipmentBagEntity> getAll();
}
