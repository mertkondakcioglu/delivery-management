package com.mertosi.delivery.service.shipment;

import com.mertosi.delivery.common.exception.NotFoundException;
import com.mertosi.delivery.model.entity.ShipmentEntity;
import com.mertosi.delivery.repository.shipment.ShipmentQueryRepository;
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
    public List<ShipmentEntity> getShipmentsInBagByBagBarcode(String bagBarcode) {
        return shipmentQueryRepository.findShipmentsInBagByBagBarcode(bagBarcode);
    }
}
