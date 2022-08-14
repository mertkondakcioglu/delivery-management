package com.mertosi.delivery.service.shipment;

import com.mertosi.delivery.model.entity.ShipmentEntity;

import java.util.List;

public interface ShipmentQueryService {
    ShipmentEntity getByBarcode(String barcode);

    List<ShipmentEntity> getAll();

    List<ShipmentEntity> getShipmentsInBagByBagBarcode(String bagBarcode);
}
