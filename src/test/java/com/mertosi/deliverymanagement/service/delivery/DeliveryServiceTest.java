package com.mertosi.deliverymanagement.service.delivery;

import com.mertosi.deliverymanagement.model.dto.request.delivery.MakeDeliveryRequest;
import com.mertosi.deliverymanagement.model.dto.request.delivery.MakeDeliveryRequestBuilder;
import com.mertosi.deliverymanagement.model.dto.response.delivery.DeliveryResponse;
import com.mertosi.deliverymanagement.model.dto.response.delivery.DeliveryResponseBuilder;
import com.mertosi.deliverymanagement.model.dto.response.delivery.RouteResponse;
import com.mertosi.deliverymanagement.model.entity.VehicleEntity;
import com.mertosi.deliverymanagement.model.entity.VehicleEntityBuilder;
import com.mertosi.deliverymanagement.service.AbstractUnitTest;
import com.mertosi.deliverymanagement.service.delivery.bag.DeliveryBagService;
import com.mertosi.deliverymanagement.service.delivery.shipment.DeliveryShipmentService;
import com.mertosi.deliverymanagement.service.vehicle.VehicleQueryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DeliveryServiceTest extends AbstractUnitTest {

    @InjectMocks
    DeliveryServiceImpl deliveryService;

    @Mock
    private VehicleQueryService vehicleQueryService;
    @Mock
    private DeliveryBagService deliveryBagService;
    @Mock
    private DeliveryShipmentService deliveryShipmentService;

    @Test
    void givenValidMakeDeliveryRequest_whenMakeDeliveryShipment_thenReturnMakeDeliveryResponse() {
        MakeDeliveryRequest testMakeDeliveryRequest = MakeDeliveryRequestBuilder.getValidMakeDeliveryRequest();

        VehicleEntity testVehicleEntity = VehicleEntityBuilder.getValidVehicleEntity();
        when(vehicleQueryService.getByLicensePlate(testMakeDeliveryRequest.getPlate())).thenReturn(testVehicleEntity);

        deliveryService.makeDelivery(testMakeDeliveryRequest);

        verify(deliveryShipmentService, times(2)).delivery(any(RouteResponse.class), any(DeliveryResponse.class));
    }

    @Test
    void givenValidMakeDeliveryRequest_whenMakeDeliveryBag_thenReturnMakeDeliveryResponse() {
        MakeDeliveryRequest testMakeDeliveryRequest = MakeDeliveryRequestBuilder.getValidMakeDeliveryRequest();

        VehicleEntity testVehicleEntity = VehicleEntityBuilder.getValidVehicleEntity();
        when(vehicleQueryService.getByLicensePlate(testMakeDeliveryRequest.getPlate())).thenReturn(testVehicleEntity);

        deliveryService.makeDelivery(testMakeDeliveryRequest);

        verify(deliveryBagService, times(2)).delivery(any(RouteResponse.class), any(DeliveryResponse.class));
    }

    @Test
    void givenValidShipmentBarcode_whenIsDeliveryShipment_thenReturnTrue() {
        DeliveryResponse testDeliveryResponse = new DeliveryResponseBuilder()
                .withBarcode("P1234567890")
                .build();

        assertThat(testDeliveryResponse.isDeliveryShipment()).isTrue();
    }

    @Test
    void givenValidBagBarcode_whenIsDeliveryShipment_thenReturnFalse() {
        DeliveryResponse testDeliveryResponse = new DeliveryResponseBuilder()
                .withBarcode("C123456")
                .build();

        assertThat(testDeliveryResponse.isDeliveryShipment()).isFalse();
    }

    @Test
    void givenValidBagBarcode_whenIsDeliveryBag_thenReturnTrue() {
        DeliveryResponse testDeliveryResponse = new DeliveryResponseBuilder()
                .withBarcode("C123456")
                .build();

        assertThat(testDeliveryResponse.isDeliveryBag()).isTrue();
    }

    @Test
    void givenValidShipmentBarcode_whenIsDeliveryBag_thenReturnFalse() {
        DeliveryResponse testDeliveryResponse = new DeliveryResponseBuilder()
                .withBarcode("P1234567890")
                .build();

        assertThat(testDeliveryResponse.isDeliveryBag()).isFalse();
    }
}
