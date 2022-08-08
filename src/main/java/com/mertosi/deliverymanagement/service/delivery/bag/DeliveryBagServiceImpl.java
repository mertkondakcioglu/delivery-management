package com.mertosi.deliverymanagement.service.delivery.bag;

import com.mertosi.deliverymanagement.common.enums.BagStatus;
import com.mertosi.deliverymanagement.common.enums.DeliveryPoint;
import com.mertosi.deliverymanagement.common.enums.ShipmentStatus;
import com.mertosi.deliverymanagement.model.dto.response.delivery.DeliveryResponse;
import com.mertosi.deliverymanagement.model.dto.response.delivery.RouteResponse;
import com.mertosi.deliverymanagement.model.entity.BagEntity;
import com.mertosi.deliverymanagement.model.entity.ShipmentEntity;
import com.mertosi.deliverymanagement.service.bag.BagCommandService;
import com.mertosi.deliverymanagement.service.bag.BagQueryService;
import com.mertosi.deliverymanagement.service.delivery.error.DeliveryErrorService;
import com.mertosi.deliverymanagement.service.shipment.ShipmentCommandService;
import com.mertosi.deliverymanagement.service.shipment.ShipmentQueryService;
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
            List<ShipmentEntity> packagesInBag = shipmentQueryService.getPackagesInBagByBagBarcode(bagEntity.getBarcode());
            packagesInBag.forEach(shipmentEntity -> shipmentCommandService.updateStatus(shipmentEntity, ShipmentStatus.UNLOADED));
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
            List<ShipmentEntity> packagesInBag = shipmentQueryService.getPackagesInBagByBagBarcode(bagEntity.getBarcode());
            boolean isBagTotallyUnloaded = packagesInBag.stream()
                    .allMatch(shipmentEntity -> ShipmentStatus.UNLOADED.equals(shipmentEntity.getStatus()));

            if (isBagTotallyUnloaded) {
                bagCommandService.updateStatus(bagEntity, BagStatus.UNLOADED);
            }
        });
    }
}
