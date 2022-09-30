package com.mertosi.delivery.service.delivery;

import com.mertosi.delivery.common.mapper.delivery.DeliveryResponseMapper;
import com.mertosi.delivery.model.dto.request.delivery.MakeDeliveryRequest;
import com.mertosi.delivery.model.dto.response.delivery.MakeDeliveryResponse;
import com.mertosi.delivery.service.delivery.bag.DeliveryBagService;
import com.mertosi.delivery.service.delivery.vehicle.VehicleProducerService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryProvider deliveryProvider;
    private final DeliveryBagService deliveryBagService;
    private final VehicleProducerService vehicleProducerService;

    private final DeliveryResponseMapper mapper = Mappers.getMapper(DeliveryResponseMapper.class);

    @Override
    public MakeDeliveryResponse makeDelivery(MakeDeliveryRequest request) {
        vehicleProducerService.sendMessage(request.getPlate());

        MakeDeliveryResponse response = mapper.map(request);
        response.getRoute().forEach(route -> route.getDeliveries()
                .forEach(delivery -> deliveryProvider.getDelivery(delivery.getType()).delivery(route, delivery)));

        deliveryBagService.checkBagsStatusAfterDelivery();
        return response;
    }
}
