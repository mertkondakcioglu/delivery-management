package com.mertosi.delivery.service.deliverypoint;

import com.mertosi.delivery.common.exception.NotFoundException;
import com.mertosi.delivery.model.entity.DeliveryPointEntity;
import com.mertosi.delivery.repository.deliverypoint.DeliveryPointQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryPointQueryServiceImpl implements DeliveryPointQueryService {

    private final DeliveryPointQueryRepository deliveryPointQueryRepository;

    @Override
    public DeliveryPointEntity getByValue(Integer value) {
        return deliveryPointQueryRepository.findByValue(value)
                .orElseThrow(() -> new NotFoundException(value.toString()));
    }
}
