package com.mertosi.deliverymanagement.service.shipment;

import com.mertosi.deliverymanagement.model.entity.ShipmentEntity;

import java.util.List;

public interface ShipmentQueryService {
    ShipmentEntity getByBarcode(String barcode);

    List<ShipmentEntity> getAll();

    List<ShipmentEntity> getShipmentsInBagByBagBarcode(String bagBarcode);
}
