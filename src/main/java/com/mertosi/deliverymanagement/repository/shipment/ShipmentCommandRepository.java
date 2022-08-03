package com.mertosi.deliverymanagement.repository.shipment;

import com.mertosi.deliverymanagement.model.entity.ShipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentCommandRepository extends JpaRepository<ShipmentEntity, Long> {
}
