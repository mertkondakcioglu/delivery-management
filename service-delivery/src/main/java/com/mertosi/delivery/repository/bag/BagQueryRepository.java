package com.mertosi.delivery.repository.bag;

import com.mertosi.delivery.common.enums.BagStatus;
import com.mertosi.delivery.model.entity.BagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BagQueryRepository extends JpaRepository<BagEntity, Long> {
    Optional<BagEntity> findByBarcode(String barcode);

    List<BagEntity> findAllByStatus(BagStatus status);
}
