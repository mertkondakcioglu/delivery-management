package com.mertosi.deliverymanagement.service.bag;

import com.mertosi.deliverymanagement.common.enums.BagStatus;
import com.mertosi.deliverymanagement.model.entity.BagEntity;

import java.util.List;

public interface BagQueryService {
    BagEntity getByBarcode(String barcode);

    List<BagEntity> getAllByStatus(BagStatus status);
}
