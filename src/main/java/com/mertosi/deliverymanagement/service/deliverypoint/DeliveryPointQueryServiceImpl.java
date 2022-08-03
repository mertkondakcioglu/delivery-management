package com.mertosi.deliverymanagement.service.deliverypoint;

import com.mertosi.deliverymanagement.common.exception.NotFoundException;
import com.mertosi.deliverymanagement.model.entity.DeliveryPointEntity;
import com.mertosi.deliverymanagement.repository.deliverypoint.DeliveryPointQueryRepository;
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
