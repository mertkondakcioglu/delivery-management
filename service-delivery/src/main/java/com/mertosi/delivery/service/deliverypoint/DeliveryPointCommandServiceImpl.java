package com.mertosi.delivery.service.deliverypoint;

import com.mertosi.delivery.common.mapper.deliverypoint.DeliveryPointRequestMapper;
import com.mertosi.delivery.model.dto.request.DeliveryPointRequest;
import com.mertosi.delivery.model.entity.DeliveryPointEntity;
import com.mertosi.delivery.repository.deliverypoint.DeliveryPointCommandRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryPointCommandServiceImpl implements DeliveryPointCommandService {

    private final DeliveryPointCommandRepository deliveryPointCommandRepository;

    private final DeliveryPointRequestMapper mapper = Mappers.getMapper(DeliveryPointRequestMapper.class);

    @Override
    public DeliveryPointEntity create(DeliveryPointRequest request) {
        DeliveryPointEntity deliveryPointEntity = mapper.map(request);
        return deliveryPointCommandRepository.save(deliveryPointEntity);
    }
}
