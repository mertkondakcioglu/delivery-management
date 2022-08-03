package com.mertosi.deliverymanagement.repository.deliverypoint;

import com.mertosi.deliverymanagement.model.entity.DeliveryPointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliveryPointQueryRepository extends JpaRepository<DeliveryPointEntity, Long> {
    Optional<DeliveryPointEntity> findByValue(Integer value);
}
