package com.mertosi.delivery.repository.delivery;

import com.mertosi.delivery.model.entity.DeliveryErrorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryErrorRepository extends JpaRepository<DeliveryErrorEntity, Long> {
}
