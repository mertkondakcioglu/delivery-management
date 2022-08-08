package com.mertosi.deliverymanagement.service.delivery;

import com.mertosi.deliverymanagement.common.enums.DeliveryPoint;
import com.mertosi.deliverymanagement.common.enums.ShipmentStatus;
import com.mertosi.deliverymanagement.model.dto.response.delivery.DeliveryResponse;
import com.mertosi.deliverymanagement.model.dto.response.delivery.DeliveryResponseBuilder;
import com.mertosi.deliverymanagement.model.dto.response.delivery.RouteResponse;
import com.mertosi.deliverymanagement.model.dto.response.delivery.RouteResponseBuilder;
import com.mertosi.deliverymanagement.model.entity.*;
import com.mertosi.deliverymanagement.service.AbstractUnitTest;
import com.mertosi.deliverymanagement.service.delivery.error.DeliveryErrorService;
import com.mertosi.deliverymanagement.service.delivery.shipment.DeliveryShipmentServiceImpl;
import com.mertosi.deliverymanagement.service.shipment.ShipmentCommandService;
import com.mertosi.deliverymanagement.service.shipment.ShipmentQueryService;
import com.mertosi.deliverymanagement.service.shipmentbag.ShipmentBagQueryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class DeliveryShipmentServiceTest extends AbstractUnitTest {

    @InjectMocks
    DeliveryShipmentServiceImpl deliveryShipmentService;

    @Mock
    private ShipmentCommandService shipmentCommandService;
    @Mock
    private ShipmentQueryService shipmentQueryService;
    @Mock
    private ShipmentBagQueryService shipmentBagQueryService;
    @Mock
    private DeliveryErrorService deliveryErrorService;

    @Test
    void givenValidShipment_whenDeliveryShipment_thenUpdateStatus() {
        RouteResponse testRouteResponse = RouteResponseBuilder.getValidRouteResponse();
        testRouteResponse.setDeliveryPoint(DeliveryPoint.DISTRIBUTION_CENTER.getValue());
        DeliveryResponse testDeliveryResponse = DeliveryResponseBuilder.getValidDeliveryResponse();

        ShipmentEntity testShipmentEntity = ShipmentEntityBuilder.getValidShipmentEntity();
        testShipmentEntity.getDeliveryPoint().setValue(DeliveryPoint.DISTRIBUTION_CENTER.getValue());
        when(shipmentQueryService.getByBarcode(anyString())).thenReturn(testShipmentEntity);
        when(shipmentCommandService.updateStatus(any(ShipmentEntity.class), any(ShipmentStatus.class))).thenReturn(testShipmentEntity);

        ShipmentBagEntity testShipmentBagEntity = ShipmentBagEntityBuilder.getValidShipmentBagEntity();
        when(shipmentBagQueryService.getByBarcode(any(ShipmentEntity.class))).thenReturn(testShipmentBagEntity);

        deliveryShipmentService.delivery(testRouteResponse, testDeliveryResponse);
        verify(shipmentCommandService, times(2)).updateStatus(any(ShipmentEntity.class), any(ShipmentStatus.class));
    }

    @Test
    void givenInvalidShipment_whenDeliveryShipment_thenCreateErrorLog() {
        RouteResponse testRouteResponse = RouteResponseBuilder.getValidRouteResponse();
        testRouteResponse.setDeliveryPoint(DeliveryPoint.TRANSFER_CENTER.getValue());
        DeliveryResponse testDeliveryResponse = DeliveryResponseBuilder.getValidDeliveryResponse();

        ShipmentEntity testShipmentEntity = ShipmentEntityBuilder.getValidShipmentEntity();
        when(shipmentQueryService.getByBarcode(anyString())).thenReturn(testShipmentEntity);
        when(shipmentCommandService.updateStatus(any(ShipmentEntity.class), any(ShipmentStatus.class))).thenReturn(testShipmentEntity);
        when(shipmentBagQueryService.getByBarcode(any(ShipmentEntity.class))).thenReturn(null);

        DeliveryErrorEntity testDeliveryErrorEntity = DeliveryErrorEntityBuilder.getValidDeliveryErrorEntity();
        when(deliveryErrorService.create(anyString(), any(Integer.class))).thenReturn(testDeliveryErrorEntity);

        deliveryShipmentService.delivery(testRouteResponse, testDeliveryResponse);
        verify(deliveryErrorService, times(1)).create(anyString(), any(Integer.class));
    }

    @Test
    void givenValidDeliveryPoint_whenIsShipmentLoadable_thenReturnTrue() {
        ShipmentEntity testShipmentEntity = ShipmentEntityBuilder.getValidShipmentEntity();
        testShipmentEntity.getDeliveryPoint().setValue(DeliveryPoint.BRANCH.getValue());
        when(shipmentQueryService.getByBarcode(anyString())).thenReturn(testShipmentEntity);
        when(shipmentBagQueryService.getByBarcode(any(ShipmentEntity.class))).thenReturn(null);

        boolean isLoadable = deliveryShipmentService.isLoadable(testShipmentEntity, DeliveryPoint.BRANCH.getValue());
        assertThat(isLoadable).isTrue();
    }

    @Test
    void givenValidShipmentBagEntityAndInvalidDeliveryPoint_whenIsShipmentLoadable_thenReturnTrue() {
        ShipmentEntity testShipmentEntity = ShipmentEntityBuilder.getValidShipmentEntity();
        testShipmentEntity.getDeliveryPoint().setValue(DeliveryPoint.TRANSFER_CENTER.getValue());
        when(shipmentQueryService.getByBarcode(anyString())).thenReturn(testShipmentEntity);

        ShipmentBagEntity testShipmentBagEntity = ShipmentBagEntityBuilder.getValidShipmentBagEntity();
        when(shipmentBagQueryService.getByBarcode(any(ShipmentEntity.class))).thenReturn(testShipmentBagEntity);

        boolean isLoadable = deliveryShipmentService.isLoadable(testShipmentEntity, DeliveryPoint.TRANSFER_CENTER.getValue());
        assertThat(isLoadable).isTrue();
    }

    @Test
    void givenInvalidRouteDeliveryPoint_whenIsShipmentLoadable_thenReturnFalse() {
        ShipmentEntity testShipmentEntity = ShipmentEntityBuilder.getValidShipmentEntity();
        testShipmentEntity.getDeliveryPoint().setValue(DeliveryPoint.BRANCH.getValue());
        when(shipmentQueryService.getByBarcode(anyString())).thenReturn(testShipmentEntity);
        when(shipmentBagQueryService.getByBarcode(any(ShipmentEntity.class))).thenReturn(null);

        boolean isLoadable = deliveryShipmentService.isLoadable(testShipmentEntity, DeliveryPoint.DISTRIBUTION_CENTER.getValue());
        assertThat(isLoadable).isFalse();
    }

    @Test
    void givenInvalidShipmentBagEntityAndInvalidDeliveryPoint_whenIsShipmentLoadable_thenReturnFalse() {
        ShipmentEntity testShipmentEntity = ShipmentEntityBuilder.getValidShipmentEntity();
        testShipmentEntity.getDeliveryPoint().setValue(DeliveryPoint.TRANSFER_CENTER.getValue());

        when(shipmentQueryService.getByBarcode(anyString())).thenReturn(testShipmentEntity);
        when(shipmentBagQueryService.getByBarcode(any(ShipmentEntity.class))).thenReturn(null);

        boolean isLoadable = deliveryShipmentService.isLoadable(testShipmentEntity, DeliveryPoint.TRANSFER_CENTER.getValue());
        assertThat(isLoadable).isFalse();
    }
}
