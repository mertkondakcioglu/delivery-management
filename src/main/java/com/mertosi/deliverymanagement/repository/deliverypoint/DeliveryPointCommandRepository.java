package com.mertosi.deliverymanagement.repository.deliverypoint;

import com.mertosi.deliverymanagement.model.entity.DeliveryPointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryPointCommandRepository extends JpaRepository<DeliveryPointEntity, Long> {
}
