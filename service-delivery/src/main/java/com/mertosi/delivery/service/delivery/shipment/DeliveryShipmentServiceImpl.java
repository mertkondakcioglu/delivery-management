package com.mertosi.delivery.service.delivery.shipment;

import com.mertosi.delivery.common.enums.DeliveryPoint;
import com.mertosi.delivery.common.enums.DeliveryType;
import com.mertosi.delivery.common.enums.ShipmentStatus;
import com.mertosi.delivery.model.dto.response.delivery.DeliveryResponse;
import com.mertosi.delivery.model.dto.response.delivery.RouteResponse;
import com.mertosi.delivery.model.entity.ShipmentBagEntity;
import com.mertosi.delivery.model.entity.ShipmentEntity;
import com.mertosi.delivery.service.delivery.error.DeliveryErrorService;
import com.mertosi.delivery.service.shipment.ShipmentCommandService;
import com.mertosi.delivery.service.shipment.ShipmentQueryService;
import com.mertosi.delivery.service.shipmentbag.ShipmentBagQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DeliveryShipmentServiceImpl implements DeliveryShipmentService {

    private final ShipmentCommandService shipmentCommandService;
    private final ShipmentQueryService shipmentQueryService;
    private final ShipmentBagQueryService shipmentBagQueryService;
    private final DeliveryErrorService deliveryErrorService;

    @Override
    public void delivery(RouteResponse routeResponse, DeliveryResponse deliveryResponse) {
        ShipmentEntity shipmentEntity = shipmentQueryService.getByBarcode(deliveryResponse.getBarcode());
        shipmentEntity = shipmentCommandService.updateStatus(shipmentEntity, ShipmentStatus.LOADED);

        if (isLoadable(shipmentEntity, routeResponse.getDeliveryPoint())) {
            shipmentEntity = shipmentCommandService.updateStatus(shipmentEntity, ShipmentStatus.UNLOADED);
        } else {
            deliveryErrorService.create(deliveryResponse.getBarcode(), routeResponse.getDeliveryPoint());
        }

        deliveryResponse.setState(shipmentEntity.getStatus().getValue());
    }

    @Override
    public boolean isLoadable(ShipmentEntity shipmentEntity, Integer routeDeliveryPoint) {
        Integer deliveryPoint = shipmentEntity.getDeliveryPoint().getValue();
        ShipmentBagEntity shipmentBagEntity = shipmentBagQueryService.getByBarcode(shipmentEntity);

        return deliveryPoint.equals(routeDeliveryPoint) &&
                (!DeliveryPoint.TRANSFER_CENTER.getValue().equals(deliveryPoint) || shipmentBagEntity != null);
    }

    @Override
    public DeliveryType type() {
        return DeliveryType.SHIPMENT;
    }
}
