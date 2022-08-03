package com.mertosi.deliverymanagement.service.bag;

import com.mertosi.deliverymanagement.common.enums.BagStatus;
import com.mertosi.deliverymanagement.common.exception.DeliveryManagementException;
import com.mertosi.deliverymanagement.common.mapper.bag.BagRequestMapper;
import com.mertosi.deliverymanagement.model.dto.request.BagRequest;
import com.mertosi.deliverymanagement.model.entity.BagEntity;
import com.mertosi.deliverymanagement.model.entity.DeliveryPointEntity;
import com.mertosi.deliverymanagement.repository.bag.BagCommandRepository;
import com.mertosi.deliverymanagement.service.deliverypoint.DeliveryPointQueryService;
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
            throw new DeliveryManagementException("Bag unloaded status can not change");
        }

        bagEntity.setStatus(status);
        return bagCommandRepository.save(bagEntity);
    }
}
