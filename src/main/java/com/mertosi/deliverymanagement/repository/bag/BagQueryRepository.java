package com.mertosi.deliverymanagement.repository.bag;

import com.mertosi.deliverymanagement.common.enums.BagStatus;
import com.mertosi.deliverymanagement.model.entity.BagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BagQueryRepository extends JpaRepository<BagEntity, Long> {
    Optional<BagEntity> findByBarcode(String barcode);

    List<BagEntity> findAllByStatus(BagStatus status);
}
