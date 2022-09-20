package com.mertosi.delivery.service.delivery.bag;

import com.mertosi.delivery.common.enums.DeliveryType;
import com.mertosi.delivery.service.delivery.error.DeliveryErrorService;
import com.mertosi.delivery.service.shipment.ShipmentCommandService;
import com.mertosi.delivery.service.shipment.ShipmentQueryService;
import com.mertosi.delivery.common.enums.BagStatus;
import com.mertosi.delivery.common.enums.DeliveryPoint;
import com.mertosi.delivery.common.enums.ShipmentStatus;
import com.mertosi.delivery.model.dto.response.delivery.DeliveryResponse;
import com.mertosi.delivery.model.dto.response.delivery.RouteResponse;
import com.mertosi.delivery.model.entity.BagEntity;
import com.mertosi.delivery.model.entity.ShipmentEntity;
import com.mertosi.delivery.service.bag.BagCommandService;
import com.mertosi.delivery.service.bag.BagQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DeliveryBagServiceImpl implements DeliveryBagService {

    private final BagCommandService bagCommandService;
    private final BagQueryService bagQueryService;
    private final ShipmentCommandService shipmentCommandService;
    private final ShipmentQueryService shipmentQueryService;
    private final DeliveryErrorService deliveryErrorService;

    @Override
    public void delivery(RouteResponse routeResponse, DeliveryResponse deliveryResponse) {
        BagEntity bagEntity = bagQueryService.getByBarcode(deliveryResponse.getBarcode());
        bagEntity = bagCommandService.updateStatus(bagEntity, BagStatus.LOADED);

        if (isLoadable(bagEntity, routeResponse.getDeliveryPoint())) {
            List<ShipmentEntity> shipmentsInBag = shipmentQueryService.getShipmentsInBagByBagBarcode(bagEntity.getBarcode());
            shipmentsInBag.forEach(shipmentEntity -> shipmentCommandService.updateStatus(shipmentEntity, ShipmentStatus.UNLOADED));
            bagEntity = bagCommandService.updateStatus(bagEntity, BagStatus.UNLOADED);
        } else {
            deliveryErrorService.create(deliveryResponse.getBarcode(), routeResponse.getDeliveryPoint());
        }

        deliveryResponse.setState(bagEntity.getStatus().getValue());
    }

    @Override
    public boolean isLoadable(BagEntity bagEntity, Integer routeDeliveryPoint) {
        Integer deliveryPoint = bagEntity.getDeliveryPoint().getValue();
        return deliveryPoint.equals(routeDeliveryPoint) && !DeliveryPoint.BRANCH.getValue().equals(deliveryPoint);
    }

    @Override
    public void checkBagsStatusAfterDelivery() {
        List<BagEntity> bagEntities = bagQueryService.getAllByStatus(BagStatus.CREATED);
        bagEntities.forEach(bagEntity -> {
            List<ShipmentEntity> shipmentsInBag = shipmentQueryService.getShipmentsInBagByBagBarcode(bagEntity.getBarcode());
            boolean isBagTotallyUnloaded = shipmentsInBag.stream()
                    .allMatch(shipmentEntity -> ShipmentStatus.UNLOADED.equals(shipmentEntity.getStatus()));

            if (isBagTotallyUnloaded) {
                bagCommandService.updateStatus(bagEntity, BagStatus.UNLOADED);
            }
        });
    }

    @Override
    public DeliveryType type() {
        return DeliveryType.BAG;
    }
}
