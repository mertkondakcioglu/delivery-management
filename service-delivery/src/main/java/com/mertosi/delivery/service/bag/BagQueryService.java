package com.mertosi.delivery.service.bag;

import com.mertosi.delivery.common.enums.BagStatus;
import com.mertosi.delivery.model.entity.BagEntity;

import java.util.List;

public interface BagQueryService {
    BagEntity getByBarcode(String barcode);

    List<BagEntity> getAllByStatus(BagStatus status);
}
