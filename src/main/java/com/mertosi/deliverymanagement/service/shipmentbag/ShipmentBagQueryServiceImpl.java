package com.mertosi.deliverymanagement.service.shipmentbag;

import com.mertosi.deliverymanagement.model.entity.ShipmentBagEntity;
import com.mertosi.deliverymanagement.model.entity.ShipmentEntity;
import com.mertosi.deliverymanagement.repository.shipmentbag.ShipmentBagQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipmentBagQueryServiceImpl implements ShipmentBagQueryService {

    private final ShipmentBagQueryRepository shipmentQueryRepository;

    @Override
    public ShipmentBagEntity getByBarcode(ShipmentEntity barcode) {
        return shipmentQueryRepository.findByBarcode(barcode);
    }

    @Override
    public List<ShipmentBagEntity> getAll() {
        return shipmentQueryRepository.findAll();
    }
}
