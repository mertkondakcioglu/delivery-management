package com.mertosi.deliverymanagement.service.delivery;

import com.mertosi.deliverymanagement.common.enums.BagStatus;
import com.mertosi.deliverymanagement.common.enums.DeliveryPoint;
import com.mertosi.deliverymanagement.common.enums.ShipmentStatus;
import com.mertosi.deliverymanagement.model.dto.response.delivery.DeliveryResponse;
import com.mertosi.deliverymanagement.model.dto.response.delivery.DeliveryResponseBuilder;
import com.mertosi.deliverymanagement.model.dto.response.delivery.RouteResponse;
import com.mertosi.deliverymanagement.model.dto.response.delivery.RouteResponseBuilder;
import com.mertosi.deliverymanagement.model.entity.*;
import com.mertosi.deliverymanagement.service.AbstractUnitTest;
import com.mertosi.deliverymanagement.service.bag.BagCommandService;
import com.mertosi.deliverymanagement.service.bag.BagQueryService;
import com.mertosi.deliverymanagement.service.delivery.bag.DeliveryBagServiceImpl;
import com.mertosi.deliverymanagement.service.delivery.error.DeliveryErrorService;
import com.mertosi.deliverymanagement.service.shipment.ShipmentCommandService;
import com.mertosi.deliverymanagement.service.shipment.ShipmentQueryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class DeliveryBagServiceTest extends AbstractUnitTest {

    @InjectMocks
    DeliveryBagServiceImpl deliveryBagService;

    @Mock
    private ShipmentCommandService shipmentCommandService;
    @Mock
    private ShipmentQueryService shipmentQueryService;
    @Mock
    private BagCommandService bagCommandService;
    @Mock
    private BagQueryService bagQueryService;
    @Mock
    private DeliveryErrorService deliveryErrorService;

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
        when(shipmentQueryService.getShipmentsInBagByBagBarcode(anyString())).thenReturn(testShipmentEntities);

        ShipmentEntity testShipmentEntity = ShipmentEntityBuilder.getValidShipmentEntity();
        when(shipmentCommandService.updateStatus(any(ShipmentEntity.class), any(ShipmentStatus.class))).thenReturn(testShipmentEntity);

        deliveryBagService.delivery(testRouteResponse, testDeliveryResponse);
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

        deliveryBagService.delivery(testRouteResponse, testDeliveryResponse);
        verify(deliveryErrorService, times(1)).create(anyString(), any(Integer.class));
    }

    @Test
    void givenValidDeliveryPoint_whenIsBagLoadable_thenReturnTrue() {
        BagEntity testBagEntity = BagEntityBuilder.getValidBagEntity();
        testBagEntity.getDeliveryPoint().setValue(DeliveryPoint.TRANSFER_CENTER.getValue());
        when(bagQueryService.getByBarcode(anyString())).thenReturn(testBagEntity);

        boolean isLoadable = deliveryBagService.isLoadable(testBagEntity, DeliveryPoint.TRANSFER_CENTER.getValue());
        assertThat(isLoadable).isTrue();
    }

    @Test
    void givenInvalidRouteDeliveryPoint_whenIsBagLoadable_thenReturnFalse() {
        BagEntity testBagEntity = BagEntityBuilder.getValidBagEntity();
        testBagEntity.getDeliveryPoint().setValue(DeliveryPoint.TRANSFER_CENTER.getValue());
        when(bagQueryService.getByBarcode(anyString())).thenReturn(testBagEntity);

        boolean isLoadable = deliveryBagService.isLoadable(testBagEntity, DeliveryPoint.BRANCH.getValue());
        assertThat(isLoadable).isFalse();
    }

    @Test
    void givenInvalidDeliveryPoint_whenIsBagLoadable_thenReturnFalse() {
        BagEntity testBagEntity = BagEntityBuilder.getValidBagEntity();
        testBagEntity.getDeliveryPoint().setValue(DeliveryPoint.BRANCH.getValue());
        when(bagQueryService.getByBarcode(anyString())).thenReturn(testBagEntity);

        boolean isLoadable = deliveryBagService.isLoadable(testBagEntity, DeliveryPoint.BRANCH.getValue());
        assertThat(isLoadable).isFalse();
    }

    @Test
    void givenMakeDeliveryShipmentsInBagWithoutBag_whenCheckBagsStatusAfterDelivery_thenUpdateStatus() {
        List<BagEntity> testBagEntities = BagEntityBuilder.getValidBagEntities();
        when(bagQueryService.getAllByStatus(any(BagStatus.class))).thenReturn(testBagEntities);

        List<ShipmentEntity> testShipmentEntities = ShipmentEntityBuilder.getValidShipmentEntities();
        testShipmentEntities.forEach(shipmentEntity -> shipmentEntity.setStatus(ShipmentStatus.UNLOADED));
        when(shipmentQueryService.getShipmentsInBagByBagBarcode(anyString())).thenReturn(testShipmentEntities);

        BagEntity testBagEntity = BagEntityBuilder.getValidBagEntity();
        when(bagCommandService.updateStatus(any(BagEntity.class), any(BagStatus.class))).thenReturn(testBagEntity);

        deliveryBagService.checkBagsStatusAfterDelivery();
        verify(bagCommandService, times(testBagEntities.size())).updateStatus(any(BagEntity.class), any(BagStatus.class));
    }

    @Test
    void givenMakeDeliveryWithoutShipmentsInBag_whenCheckBagsStatusAfterDelivery_thenNothing() {
        List<BagEntity> testBagEntities = BagEntityBuilder.getValidBagEntities();
        when(bagQueryService.getAllByStatus(any(BagStatus.class))).thenReturn(testBagEntities);

        List<ShipmentEntity> testShipmentEntities = ShipmentEntityBuilder.getValidShipmentEntities();
        when(shipmentQueryService.getShipmentsInBagByBagBarcode(anyString())).thenReturn(testShipmentEntities);

        deliveryBagService.checkBagsStatusAfterDelivery();
        verify(bagCommandService, never()).updateStatus(any(BagEntity.class), any(BagStatus.class));
    }
}
