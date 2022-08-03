package com.mertosi.deliverymanagement.repository.bag;

import com.mertosi.deliverymanagement.model.entity.BagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BagCommandRepository extends JpaRepository<BagEntity, Long> {
}
