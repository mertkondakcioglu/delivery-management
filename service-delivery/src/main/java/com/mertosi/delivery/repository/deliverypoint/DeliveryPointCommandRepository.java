package com.mertosi.delivery.repository.deliverypoint;

import com.mertosi.delivery.model.entity.DeliveryPointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryPointCommandRepository extends JpaRepository<DeliveryPointEntity, Long> {
}
