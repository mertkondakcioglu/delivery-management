package com.mertosi.delivery.repository.shipment;

import com.mertosi.delivery.model.entity.ShipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentCommandRepository extends JpaRepository<ShipmentEntity, Long> {
}
