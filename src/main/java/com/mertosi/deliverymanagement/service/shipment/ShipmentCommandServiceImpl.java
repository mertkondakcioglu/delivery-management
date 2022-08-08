package com.mertosi.deliverymanagement.service.shipment;

import com.mertosi.deliverymanagement.common.enums.ShipmentStatus;
import com.mertosi.deliverymanagement.common.exception.DeliveryManagementException;
import com.mertosi.deliverymanagement.common.mapper.shipment.ShipmentRequestMapper;
import com.mertosi.deliverymanagement.model.dto.request.ShipmentRequest;
import com.mertosi.deliverymanagement.model.entity.DeliveryPointEntity;
import com.mertosi.deliverymanagement.model.entity.ShipmentEntity;
import com.mertosi.deliverymanagement.repository.shipment.ShipmentCommandRepository;
import com.mertosi.deliverymanagement.service.deliverypoint.DeliveryPointQueryService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ShipmentCommandServiceImpl implements ShipmentCommandService {

    private final DeliveryPointQueryService deliveryPointQueryService;
    private final ShipmentCommandRepository shipmentCommandRepository;

    private final ShipmentRequestMapper mapper = Mappers.getMapper(ShipmentRequestMapper.class);


    @Override
    public List<ShipmentEntity> create(List<ShipmentRequest> requests) {
        List<ShipmentEntity> shipmentEntities = new ArrayList<>();

        for (ShipmentRequest request : requests) {
            ShipmentEntity shipmentEntity = mapper.map(request);

            DeliveryPointEntity deliveryPoint = deliveryPointQueryService.getByValue(request.getDeliveryPoint());
            shipmentEntity.setDeliveryPoint(deliveryPoint);

            ShipmentEntity savedShipmentEntity = shipmentCommandRepository.save(shipmentEntity);
            shipmentEntities.add(savedShipmentEntity);
        }

        return shipmentEntities;
    }

    @Override
    public ShipmentEntity updateStatus(ShipmentEntity shipmentEntity, ShipmentStatus status) {
        if (ShipmentStatus.UNLOADED.equals(shipmentEntity.getStatus())) {
            throw new DeliveryManagementException("Package unloaded status can not change");
        }

        shipmentEntity.setStatus(status);
        return shipmentCommandRepository.save(shipmentEntity);
    }
}
