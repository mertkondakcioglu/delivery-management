package com.mertosi.delivery.service.delivery.error;

import com.mertosi.delivery.model.entity.DeliveryErrorEntity;
import com.mertosi.delivery.repository.delivery.DeliveryErrorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryErrorServiceImpl implements DeliveryErrorService {

    private final DeliveryErrorRepository deliveryErrorRepository;

    @Override
    public DeliveryErrorEntity create(String barcode, Integer deliveryPoint) {
        DeliveryErrorEntity deliveryErrorEntity = DeliveryErrorEntity.builder()
                .barcode(barcode)
                .deliveryPoint(deliveryPoint)
                .build();

        DeliveryErrorEntity savedDeliveryErrorEntity = deliveryErrorRepository.save(deliveryErrorEntity);
        log.error(String.format("%s barcode is incorrectly sent to %d delivery point value!", barcode, deliveryPoint));
        return savedDeliveryErrorEntity;
    }
}
