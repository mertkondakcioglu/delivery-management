package com.mertosi.deliverymanagement.service.delivery;

import com.mertosi.deliverymanagement.model.entity.DeliveryErrorEntity;
import com.mertosi.deliverymanagement.repository.delivery.DeliveryErrorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryErrorServiceImpl implements DeliveryErrorService {

    private final DeliveryErrorRepository deliveryErrorRepository;

    @Override
    public DeliveryErrorEntity create(String barcode, Integer deliveryPoint) {
        DeliveryErrorEntity deliveryErrorEntity = DeliveryErrorEntity.builder()
                .barcode(barcode)
                .deliveryPoint(deliveryPoint)
                .build();

        return deliveryErrorRepository.save(deliveryErrorEntity);
    }
}
