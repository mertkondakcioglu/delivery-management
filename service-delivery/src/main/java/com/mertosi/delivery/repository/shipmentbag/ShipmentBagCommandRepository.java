package com.mertosi.delivery.repository.shipmentbag;

import com.mertosi.delivery.model.entity.ShipmentBagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentBagCommandRepository extends JpaRepository<ShipmentBagEntity, Long> {
}
