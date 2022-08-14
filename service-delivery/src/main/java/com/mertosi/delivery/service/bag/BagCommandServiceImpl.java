package com.mertosi.delivery.service.bag;

import com.mertosi.delivery.common.enums.BagStatus;
import com.mertosi.delivery.common.exception.DeliveryException;
import com.mertosi.delivery.common.mapper.bag.BagRequestMapper;
import com.mertosi.delivery.model.dto.request.BagRequest;
import com.mertosi.delivery.model.entity.BagEntity;
import com.mertosi.delivery.model.entity.DeliveryPointEntity;
import com.mertosi.delivery.repository.bag.BagCommandRepository;
import com.mertosi.delivery.service.deliverypoint.DeliveryPointQueryService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BagCommandServiceImpl implements BagCommandService {

    private final DeliveryPointQueryService deliveryPointQueryService;
    private final BagCommandRepository bagCommandRepository;

    private final BagRequestMapper mapper = Mappers.getMapper(BagRequestMapper.class);

    @Override
    public BagEntity create(BagRequest request) {
        BagEntity bagEntity = mapper.map(request);
        DeliveryPointEntity deliveryPoint = deliveryPointQueryService.getByValue(request.getDeliveryPoint());
        bagEntity.setDeliveryPoint(deliveryPoint);

        return bagCommandRepository.save(bagEntity);
    }

    @Override
    public BagEntity updateStatus(BagEntity bagEntity, BagStatus status) {
        if (BagStatus.UNLOADED.equals(bagEntity.getStatus())) {
            throw new DeliveryException("Bag unloaded status can not change");
        }

        bagEntity.setStatus(status);
        return bagCommandRepository.save(bagEntity);
    }
}
