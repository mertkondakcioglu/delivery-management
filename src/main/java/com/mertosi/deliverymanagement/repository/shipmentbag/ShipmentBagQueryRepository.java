package com.mertosi.deliverymanagement.repository.shipmentbag;

import com.mertosi.deliverymanagement.model.entity.ShipmentBagEntity;
import com.mertosi.deliverymanagement.model.entity.ShipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentBagQueryRepository extends JpaRepository<ShipmentBagEntity, Long> {

    ShipmentBagEntity findByBarcode(ShipmentEntity barcode);
}
