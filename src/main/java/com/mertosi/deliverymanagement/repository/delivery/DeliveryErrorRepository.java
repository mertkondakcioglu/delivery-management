package com.mertosi.deliverymanagement.repository.delivery;

import com.mertosi.deliverymanagement.model.entity.DeliveryErrorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryErrorRepository extends JpaRepository<DeliveryErrorEntity, Long> {
}
