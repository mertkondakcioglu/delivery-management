package com.mertosi.delivery.repository.bag;

import com.mertosi.delivery.model.entity.BagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BagCommandRepository extends JpaRepository<BagEntity, Long> {
}
