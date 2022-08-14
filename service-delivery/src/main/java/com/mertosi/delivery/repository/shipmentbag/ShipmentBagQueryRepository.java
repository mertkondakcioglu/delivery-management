package com.mertosi.delivery.repository.shipmentbag;

import com.mertosi.delivery.model.entity.ShipmentBagEntity;
import com.mertosi.delivery.model.entity.ShipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentBagQueryRepository extends JpaRepository<ShipmentBagEntity, Long> {

    ShipmentBagEntity findByBarcode(ShipmentEntity barcode);
}
