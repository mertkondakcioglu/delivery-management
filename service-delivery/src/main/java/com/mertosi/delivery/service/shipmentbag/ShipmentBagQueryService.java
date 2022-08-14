package com.mertosi.delivery.service.shipmentbag;

import com.mertosi.delivery.model.entity.ShipmentBagEntity;
import com.mertosi.delivery.model.entity.ShipmentEntity;

import java.util.List;

public interface ShipmentBagQueryService {
    ShipmentBagEntity getByBarcode(ShipmentEntity barcode);

    List<ShipmentBagEntity> getAll();
}
