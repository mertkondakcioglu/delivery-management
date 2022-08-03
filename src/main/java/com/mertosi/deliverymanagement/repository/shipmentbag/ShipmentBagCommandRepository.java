package com.mertosi.deliverymanagement.repository.shipmentbag;

import com.mertosi.deliverymanagement.model.entity.ShipmentBagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentBagCommandRepository extends JpaRepository<ShipmentBagEntity, Long> {
}
