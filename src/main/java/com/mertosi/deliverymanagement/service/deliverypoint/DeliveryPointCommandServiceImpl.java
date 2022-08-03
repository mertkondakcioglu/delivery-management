package com.mertosi.deliverymanagement.service.deliverypoint;

import com.mertosi.deliverymanagement.common.mapper.deliverypoint.DeliveryPointRequestMapper;
import com.mertosi.deliverymanagement.model.dto.request.DeliveryPointRequest;
import com.mertosi.deliverymanagement.model.entity.DeliveryPointEntity;
import com.mertosi.deliverymanagement.repository.deliverypoint.DeliveryPointCommandRepository;
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
