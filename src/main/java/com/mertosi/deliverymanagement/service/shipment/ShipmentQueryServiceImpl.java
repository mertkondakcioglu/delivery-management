package com.mertosi.deliverymanagement.service.shipment;

import com.mertosi.deliverymanagement.common.exception.NotFoundException;
import com.mertosi.deliverymanagement.model.entity.ShipmentEntity;
import com.mertosi.deliverymanagement.repository.shipment.ShipmentQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShipmentQueryServiceImpl implements ShipmentQueryService {

    private final ShipmentQueryRepository shipmentQueryRepository;

    @Override
    public ShipmentEntity getByBarcode(String barcode) {
        return shipmentQueryRepository.findByBarcode(barcode)
                .orElseThrow(() -> new NotFoundException(barcode));
    }

    @Override
    public List<ShipmentEntity> getAll() {
        return shipmentQueryRepository.findAll();
    }

    @Override
    public List<ShipmentEntity> getPackagesInBagByBagBarcode(String bagBarcode) {
        return shipmentQueryRepository.findPackagesInBagByBagBarcode(bagBarcode);
    }
}
