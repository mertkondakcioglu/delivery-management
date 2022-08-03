package com.mertosi.deliverymanagement.service.delivery;

import com.mertosi.deliverymanagement.common.enums.BagStatus;
import com.mertosi.deliverymanagement.common.enums.DeliveryPoint;
import com.mertosi.deliverymanagement.common.enums.PackageStatus;
import com.mertosi.deliverymanagement.model.dto.request.delivery.MakeDeliveryRequest;
import com.mertosi.deliverymanagement.model.dto.request.delivery.MakeDeliveryRequestBuilder;
import com.mertosi.deliverymanagement.model.dto.response.delivery.*;
import com.mertosi.deliverymanagement.model.entity.*;
import com.mertosi.deliverymanagement.service.AbstractUnitTest;
import com.mertosi.deliverymanagement.service.bag.BagCommandService;
import com.mertosi.deliverymanagement.service.bag.BagQueryService;
import com.mertosi.deliverymanagement.service.shipment.ShipmentCommandService;
import com.mertosi.deliverymanagement.service.shipment.ShipmentQueryService;
import com.mertosi.deliverymanagement.service.shipmentbag.ShipmentBagQueryService;
import com.mertosi.deliverymanagement.service.vehicle.VehicleQueryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class DeliveryServiceTest extends AbstractUnitTest {

    @InjectMocks
    DeliveryServiceImpl deliveryService;

    @Mock
    private VehicleQueryService vehicleQueryService;
    @Mock
    private ShipmentCommandService shipmentCommandService;
    @Mock
    private ShipmentQueryService shipmentQueryService;
    @Mock
    private BagCommandService bagCommandService;
    @Mock
    private BagQueryService bagQueryService;
    @Mock
    private ShipmentBagQueryService shipmentBagQueryService;
    @Mock
    private DeliveryErrorService deliveryErrorService;

    @Test
    void givenValidMakeDeliveryRequest_whenMakeDelivery_thenReturnMakeDeliveryResponse() {
        MakeDeliveryRequest testMakeDeliveryRequest = MakeDeliveryRequestBuilder.getValidMakeDeliveryRequest();
        DeliveryPointEntity testDeliveryPointEntity = DeliveryPointEntityBuilder.getValidDeliveryPointEntity();

        VehicleEntity testVehicleEntity = VehicleEntityBuilder.getValidVehicleEntity();
        when(vehicleQueryService.getByLicensePlate(testMakeDeliveryRequest.getPlate())).thenReturn(testVehicleEntity);

        ShipmentEntity testShipmentEntity = ShipmentEntityBuilder.getValidShipmentEntity();
        testShipmentEntity.setDeliveryPoint(testDeliveryPointEntity);
        when(shipmentQueryService.getByBarcode(anyString())).thenReturn(testShipmentEntity);
        when(shipmentCommandService.updateStatus(any(ShipmentEntity.class), any(PackageStatus.class))).thenReturn(testShipmentEntity);

        BagEntity testBagEntity = BagEntityBuilder.getValidBagEntity();
        testBagEntity.setDeliveryPoint(testDeliveryPointEntity);
        when(bagQueryService.getByBarcode(anyString())).thenReturn(testBagEntity);
        when(bagCommandService.updateStatus(any(BagEntity.class), any(BagStatus.class))).thenReturn(testBagEntity);

        DeliveryErrorEntity testDeliveryErrorEntity = DeliveryErrorEntityBuilder.getValidDeliveryErrorEntity();
        when(deliveryErrorService.create(anyString(), any(Integer.class))).thenReturn(testDeliveryErrorEntity);

        ShipmentBagEntity testShipmentBagEntity = ShipmentBagEntityBuilder.getValidShipmentBagEntity();
        when(shipmentBagQueryService.getByBarcode(any(ShipmentEntity.class))).thenReturn(testShipmentBagEntity);

        List<BagEntity> testBagEntities = BagEntityBuilder.getValidBagEntities();
        when(bagQueryService.getAllByStatus(any(BagStatus.class))).thenReturn(testBagEntities);

        List<ShipmentEntity> testShipmentEntities = ShipmentEntityBuilder.getValidShipmentEntities();
        when(shipmentQueryService.getPackagesInBagByBagBarcode(anyString())).thenReturn(testShipmentEntities);

        MakeDeliveryResponse makeDeliveryResponse = deliveryService.makeDelivery(testMakeDeliveryRequest);

        assertThat(makeDeliveryResponse).isNotNull();
    }

    @Test
    void givenValidPackageBarcode_whenIsDeliveryPackage_thenReturnTrue() {
        String barcode = "P1234567890";
        boolean isPackage = deliveryService.isDeliveryPackage(barcode);
        assertThat(isPackage).isTrue();
    }

    @Test
    void givenValidBagBarcode_whenIsDeliveryPackage_thenReturnFalse() {
        String barcode = "C123456";
        boolean isPackage = deliveryService.isDeliveryPackage(barcode);
        assertThat(isPackage).isFalse();
    }

    @Test
    void givenValidPackage_whenDeliveryPackage_thenUpdateStatus() {
        RouteResponse testRouteResponse = RouteResponseBuilder.getValidRouteResponse();
        testRouteResponse.setDeliveryPoint(DeliveryPoint.DISTRIBUTION_CENTER.getValue());
        DeliveryResponse testDeliveryResponse = DeliveryResponseBuilder.getValidDeliveryResponse();

        ShipmentEntity testShipmentEntity = ShipmentEntityBuilder.getValidShipmentEntity();
        testShipmentEntity.getDeliveryPoint().setValue(DeliveryPoint.DISTRIBUTION_CENTER.getValue());
        when(shipmentQueryService.getByBarcode(anyString())).thenReturn(testShipmentEntity);
        when(shipmentCommandService.updateStatus(any(ShipmentEntity.class), any(PackageStatus.class))).thenReturn(testShipmentEntity);

        ShipmentBagEntity testShipmentBagEntity = ShipmentBagEntityBuilder.getValidShipmentBagEntity();
        when(shipmentBagQueryService.getByBarcode(any(ShipmentEntity.class))).thenReturn(testShipmentBagEntity);

        deliveryService.deliveryPackage(testRouteResponse, testDeliveryResponse);
        verify(shipmentCommandService, times(2)).updateStatus(any(ShipmentEntity.class), any(PackageStatus.class));
    }

    @Test
    void givenInvalidPackage_whenDeliveryPackage_thenCreateErrorLog() {
        RouteResponse testRouteResponse = RouteResponseBuilder.getValidRouteResponse();
        testRouteResponse.setDeliveryPoint(DeliveryPoint.TRANSFER_CENTER.getValue());
        DeliveryResponse testDeliveryResponse = DeliveryResponseBuilder.getValidDeliveryResponse();

        ShipmentEntity testShipmentEntity = ShipmentEntityBuilder.getValidShipmentEntity();
        when(shipmentQueryService.getByBarcode(anyString())).thenReturn(testShipmentEntity);
        when(shipmentCommandService.updateStatus(any(ShipmentEntity.class), any(PackageStatus.class))).thenReturn(testShipmentEntity);
        when(shipmentBagQueryService.getByBarcode(any(ShipmentEntity.class))).thenReturn(null);

        DeliveryErrorEntity testDeliveryErrorEntity = DeliveryErrorEntityBuilder.getValidDeliveryErrorEntity();
        when(deliveryErrorService.create(anyString(), any(Integer.class))).thenReturn(testDeliveryErrorEntity);

        deliveryService.deliveryPackage(testRouteResponse, testDeliveryResponse);
        verify(deliveryErrorService, times(1)).create(anyString(), any(Integer.class));
    }

    @Test
    void givenValidBag_whenDeliveryBag_thenUpdateStatus() {
        RouteResponse testRouteResponse = RouteResponseBuilder.getValidRouteResponse();
        testRouteResponse.setDeliveryPoint(DeliveryPoint.DISTRIBUTION_CENTER.getValue());
        DeliveryResponse testDeliveryResponse = DeliveryResponseBuilder.getValidDeliveryResponse();

        BagEntity testBagEntity = BagEntityBuilder.getValidBagEntity();
        testBagEntity.getDeliveryPoint().setValue(DeliveryPoint.DISTRIBUTION_CENTER.getValue());
        when(bagQueryService.getByBarcode(anyString())).thenReturn(testBagEntity);
        when(bagCommandService.updateStatus(any(BagEntity.class), any(BagStatus.class))).thenReturn(testBagEntity);

        List<ShipmentEntity> testShipmentEntities = ShipmentEntityBuilder.getValidShipmentEntities();
        when(shipmentQueryService.getPackagesInBagByBagBarcode(anyString())).thenReturn(testShipmentEntities);

        ShipmentEntity testShipmentEntity = ShipmentEntityBuilder.getValidShipmentEntity();
        when(shipmentCommandService.updateStatus(any(ShipmentEntity.class), any(PackageStatus.class))).thenReturn(testShipmentEntity);

        deliveryService.deliveryBag(testRouteResponse, testDeliveryResponse);
        verify(bagCommandService, times(2)).updateStatus(any(BagEntity.class), any(BagStatus.class));
    }

    @Test
    void givenInvalidBag_whenDeliveryBag_thenCreateErrorLog() {
        RouteResponse testRouteResponse = RouteResponseBuilder.getValidRouteResponse();
        testRouteResponse.setDeliveryPoint(DeliveryPoint.BRANCH.getValue());
        DeliveryResponse testDeliveryResponse = DeliveryResponseBuilder.getValidDeliveryResponse();

        BagEntity testBagEntity = BagEntityBuilder.getValidBagEntity();
        when(bagQueryService.getByBarcode(anyString())).thenReturn(testBagEntity);
        when(bagCommandService.updateStatus(any(BagEntity.class), any(BagStatus.class))).thenReturn(testBagEntity);

        DeliveryErrorEntity testDeliveryErrorEntity = DeliveryErrorEntityBuilder.getValidDeliveryErrorEntity();
        when(deliveryErrorService.create(anyString(), any(Integer.class))).thenReturn(testDeliveryErrorEntity);

        deliveryService.deliveryBag(testRouteResponse, testDeliveryResponse);
        verify(deliveryErrorService, times(1)).create(anyString(), any(Integer.class));
    }

    @Test
    void givenInvalidDeliveryPoint_whenCreateErrorLog_thenReturnErrorLog() {
        DeliveryErrorEntity testDeliveryErrorEntity = DeliveryErrorEntityBuilder.getValidDeliveryErrorEntity();
        when(deliveryErrorService.create(anyString(), any(Integer.class))).thenReturn(testDeliveryErrorEntity);

        deliveryService.createErrorLog(testDeliveryErrorEntity.getBarcode(), testDeliveryErrorEntity.getDeliveryPoint());
        verify(deliveryErrorService, times(1)).create(anyString(), any(Integer.class));
    }

    @Test
    void givenValidDeliveryPoint_whenIsPackageLoadable_thenReturnTrue() {
        ShipmentEntity testShipmentEntity = ShipmentEntityBuilder.getValidShipmentEntity();
        testShipmentEntity.getDeliveryPoint().setValue(DeliveryPoint.BRANCH.getValue());
        when(shipmentQueryService.getByBarcode(anyString())).thenReturn(testShipmentEntity);
        when(shipmentBagQueryService.getByBarcode(any(ShipmentEntity.class))).thenReturn(null);

        boolean isLoadable = deliveryService.isLoadable(testShipmentEntity, DeliveryPoint.BRANCH.getValue());
        assertThat(isLoadable).isTrue();
    }

    @Test
    void givenValidShipmentBagEntityAndInvalidDeliveryPoint_whenIsPackageLoadable_thenReturnTrue() {
        ShipmentEntity testShipmentEntity = ShipmentEntityBuilder.getValidShipmentEntity();
        testShipmentEntity.getDeliveryPoint().setValue(DeliveryPoint.TRANSFER_CENTER.getValue());
        when(shipmentQueryService.getByBarcode(anyString())).thenReturn(testShipmentEntity);

        ShipmentBagEntity testShipmentBagEntity = ShipmentBagEntityBuilder.getValidShipmentBagEntity();
        when(shipmentBagQueryService.getByBarcode(any(ShipmentEntity.class))).thenReturn(testShipmentBagEntity);

        boolean isLoadable = deliveryService.isLoadable(testShipmentEntity, DeliveryPoint.TRANSFER_CENTER.getValue());
        assertThat(isLoadable).isTrue();
    }

    @Test
    void givenInvalidRouteDeliveryPoint_whenIsPackageLoadable_thenReturnFalse() {
        ShipmentEntity testShipmentEntity = ShipmentEntityBuilder.getValidShipmentEntity();
        testShipmentEntity.getDeliveryPoint().setValue(DeliveryPoint.BRANCH.getValue());
        when(shipmentQueryService.getByBarcode(anyString())).thenReturn(testShipmentEntity);
        when(shipmentBagQueryService.getByBarcode(any(ShipmentEntity.class))).thenReturn(null);

        boolean isLoadable = deliveryService.isLoadable(testShipmentEntity, DeliveryPoint.DISTRIBUTION_CENTER.getValue());
        assertThat(isLoadable).isFalse();
    }

    @Test
    void givenInvalidShipmentBagEntityAndInvalidDeliveryPoint_whenIsPackageLoadable_thenReturnFalse() {
        ShipmentEntity testShipmentEntity = ShipmentEntityBuilder.getValidShipmentEntity();
        testShipmentEntity.getDeliveryPoint().setValue(DeliveryPoint.TRANSFER_CENTER.getValue());

        when(shipmentQueryService.getByBarcode(anyString())).thenReturn(testShipmentEntity);
        when(shipmentBagQueryService.getByBarcode(any(ShipmentEntity.class))).thenReturn(null);

        boolean isLoadable = deliveryService.isLoadable(testShipmentEntity, DeliveryPoint.TRANSFER_CENTER.getValue());
        assertThat(isLoadable).isFalse();
    }

    @Test
    void givenValidDeliveryPoint_whenIsBagLoadable_thenReturnTrue() {
        BagEntity testBagEntity = BagEntityBuilder.getValidBagEntity();
        testBagEntity.getDeliveryPoint().setValue(DeliveryPoint.TRANSFER_CENTER.getValue());
        when(bagQueryService.getByBarcode(anyString())).thenReturn(testBagEntity);

        boolean isLoadable = deliveryService.isLoadable(testBagEntity, DeliveryPoint.TRANSFER_CENTER.getValue());
        assertThat(isLoadable).isTrue();
    }

    @Test
    void givenInvalidRouteDeliveryPoint_whenIsBagLoadable_thenReturnFalse() {
        BagEntity testBagEntity = BagEntityBuilder.getValidBagEntity();
        testBagEntity.getDeliveryPoint().setValue(DeliveryPoint.TRANSFER_CENTER.getValue());
        when(bagQueryService.getByBarcode(anyString())).thenReturn(testBagEntity);

        boolean isLoadable = deliveryService.isLoadable(testBagEntity, DeliveryPoint.BRANCH.getValue());
        assertThat(isLoadable).isFalse();
    }

    @Test
    void givenInvalidDeliveryPoint_whenIsBagLoadable_thenReturnFalse() {
        BagEntity testBagEntity = BagEntityBuilder.getValidBagEntity();
        testBagEntity.getDeliveryPoint().setValue(DeliveryPoint.BRANCH.getValue());
        when(bagQueryService.getByBarcode(anyString())).thenReturn(testBagEntity);

        boolean isLoadable = deliveryService.isLoadable(testBagEntity, DeliveryPoint.BRANCH.getValue());
        assertThat(isLoadable).isFalse();
    }

    @Test
    void givenMakeDeliveryPackagesInBagWithoutBag_whenCheckBagsStatusAfterDelivery_thenUpdateStatus() {
        List<BagEntity> testBagEntities = BagEntityBuilder.getValidBagEntities();
        when(bagQueryService.getAllByStatus(any(BagStatus.class))).thenReturn(testBagEntities);

        List<ShipmentEntity> testShipmentEntities = ShipmentEntityBuilder.getValidShipmentEntities();
        testShipmentEntities.forEach(shipmentEntity -> shipmentEntity.setStatus(PackageStatus.UNLOADED));
        when(shipmentQueryService.getPackagesInBagByBagBarcode(anyString())).thenReturn(testShipmentEntities);

        BagEntity testBagEntity = BagEntityBuilder.getValidBagEntity();
        when(bagCommandService.updateStatus(any(BagEntity.class), any(BagStatus.class))).thenReturn(testBagEntity);

        deliveryService.checkBagsStatusAfterDelivery();
        verify(bagCommandService, times(testBagEntities.size())).updateStatus(any(BagEntity.class), any(BagStatus.class));
    }

    @Test
    void givenMakeDeliveryWithoutPackagesInBag_whenCheckBagsStatusAfterDelivery_thenNothing() {
        List<BagEntity> testBagEntities = BagEntityBuilder.getValidBagEntities();
        when(bagQueryService.getAllByStatus(any(BagStatus.class))).thenReturn(testBagEntities);

        List<ShipmentEntity> testShipmentEntities = ShipmentEntityBuilder.getValidShipmentEntities();
        when(shipmentQueryService.getPackagesInBagByBagBarcode(anyString())).thenReturn(testShipmentEntities);

        deliveryService.checkBagsStatusAfterDelivery();
        verify(bagCommandService, never()).updateStatus(any(BagEntity.class), any(BagStatus.class));
    }
}
