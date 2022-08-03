package com.mertosi.deliverymanagement.service.delivery;

import com.mertosi.deliverymanagement.common.enums.BagStatus;
import com.mertosi.deliverymanagement.common.enums.DeliveryPoint;
import com.mertosi.deliverymanagement.common.enums.PackageStatus;
import com.mertosi.deliverymanagement.common.mapper.delivery.DeliveryResponseMapper;
import com.mertosi.deliverymanagement.model.dto.request.delivery.MakeDeliveryRequest;
import com.mertosi.deliverymanagement.model.dto.response.delivery.DeliveryResponse;
import com.mertosi.deliverymanagement.model.dto.response.delivery.MakeDeliveryResponse;
import com.mertosi.deliverymanagement.model.dto.response.delivery.RouteResponse;
import com.mertosi.deliverymanagement.model.entity.BagEntity;
import com.mertosi.deliverymanagement.model.entity.ShipmentBagEntity;
import com.mertosi.deliverymanagement.model.entity.ShipmentEntity;
import com.mertosi.deliverymanagement.service.bag.BagCommandService;
import com.mertosi.deliverymanagement.service.bag.BagQueryService;
import com.mertosi.deliverymanagement.service.shipment.ShipmentCommandService;
import com.mertosi.deliverymanagement.service.shipment.ShipmentQueryService;
import com.mertosi.deliverymanagement.service.shipmentbag.ShipmentBagQueryService;
import com.mertosi.deliverymanagement.service.vehicle.VehicleQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DeliveryServiceImpl implements DeliveryService {

    private final VehicleQueryService vehicleQueryService;
    private final ShipmentCommandService shipmentCommandService;
    private final ShipmentQueryService shipmentQueryService;
    private final BagCommandService bagCommandService;
    private final BagQueryService bagQueryService;
    private final ShipmentBagQueryService shipmentBagQueryService;
    private final DeliveryErrorService deliveryErrorService;

    private final DeliveryResponseMapper mapper = Mappers.getMapper(DeliveryResponseMapper.class);

    @Override
    public MakeDeliveryResponse makeDelivery(MakeDeliveryRequest request) {
        vehicleQueryService.getByLicensePlate(request.getPlate());
        MakeDeliveryResponse response = mapper.map(request);

        response.getRoute().forEach(route -> route.getDeliveries().forEach(delivery -> {
            if (isDeliveryPackage(delivery.getBarcode())) {
                deliveryPackage(route, delivery);
            } else {
                deliveryBag(route, delivery);
            }
        }));

        checkBagsStatusAfterDelivery();
        return response;
    }

    public void deliveryPackage(RouteResponse routeResponse, DeliveryResponse deliveryResponse) {
        ShipmentEntity shipmentEntity = shipmentQueryService.getByBarcode(deliveryResponse.getBarcode());
        shipmentEntity = shipmentCommandService.updateStatus(shipmentEntity, PackageStatus.LOADED);

        if (isLoadable(shipmentEntity, routeResponse.getDeliveryPoint())) {
            shipmentEntity = shipmentCommandService.updateStatus(shipmentEntity, PackageStatus.UNLOADED);
        } else {
            createErrorLog(deliveryResponse.getBarcode(), routeResponse.getDeliveryPoint());
        }

        deliveryResponse.setState(shipmentEntity.getStatus().getValue());
    }

    public void deliveryBag(RouteResponse routeResponse, DeliveryResponse deliveryResponse) {
        BagEntity bagEntity = bagQueryService.getByBarcode(deliveryResponse.getBarcode());
        bagEntity = bagCommandService.updateStatus(bagEntity, BagStatus.LOADED);

        if (isLoadable(bagEntity, routeResponse.getDeliveryPoint())) {
            List<ShipmentEntity> packagesInBag = shipmentQueryService.getPackagesInBagByBagBarcode(bagEntity.getBarcode());
            packagesInBag.forEach(shipmentEntity -> shipmentCommandService.updateStatus(shipmentEntity, PackageStatus.UNLOADED));
            bagEntity = bagCommandService.updateStatus(bagEntity, BagStatus.UNLOADED);
        } else {
            createErrorLog(deliveryResponse.getBarcode(), routeResponse.getDeliveryPoint());
        }

        deliveryResponse.setState(bagEntity.getStatus().getValue());
    }

    public boolean isDeliveryPackage(String barcode) {
        return barcode.startsWith("P") && barcode.length() == 11;
    }

    public void createErrorLog(String barcode, Integer deliveryPoint) {
        deliveryErrorService.create(barcode, deliveryPoint);
        log.error(String.format("%s barcode is incorrectly sent to %d delivery point value!", barcode, deliveryPoint));
    }


    public boolean isLoadable(ShipmentEntity shipmentEntity, Integer routeDeliveryPoint) {
        Integer deliveryPoint = shipmentEntity.getDeliveryPoint().getValue();
        ShipmentBagEntity shipmentBagEntity = shipmentBagQueryService.getByBarcode(shipmentEntity);

        return deliveryPoint.equals(routeDeliveryPoint) &&
                (!DeliveryPoint.TRANSFER_CENTER.getValue().equals(deliveryPoint) || shipmentBagEntity != null);
    }

    public boolean isLoadable(BagEntity bagEntity, Integer routeDeliveryPoint) {
        Integer deliveryPoint = bagEntity.getDeliveryPoint().getValue();
        return deliveryPoint.equals(routeDeliveryPoint) && !DeliveryPoint.BRANCH.getValue().equals(deliveryPoint);
    }

    public void checkBagsStatusAfterDelivery() {
        List<BagEntity> bagEntities = bagQueryService.getAllByStatus(BagStatus.CREATED);
        bagEntities.forEach(bagEntity -> {
            List<ShipmentEntity> packagesInBag = shipmentQueryService.getPackagesInBagByBagBarcode(bagEntity.getBarcode());
            boolean isBagTotallyUnloaded = packagesInBag.stream()
                    .allMatch(shipmentEntity -> PackageStatus.UNLOADED.equals(shipmentEntity.getStatus()));

            if (isBagTotallyUnloaded) {
                bagCommandService.updateStatus(bagEntity, BagStatus.UNLOADED);
            }
        });
    }
}
