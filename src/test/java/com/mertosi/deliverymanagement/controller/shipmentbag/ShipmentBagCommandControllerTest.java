package com.mertosi.deliverymanagement.controller.shipmentbag;

import com.mertosi.deliverymanagement.controller.AbstractControllerTest;
import com.mertosi.deliverymanagement.model.dto.request.ShipmentBagRequest;
import com.mertosi.deliverymanagement.model.dto.request.ShipmentBagRequestBuilder;
import com.mertosi.deliverymanagement.model.entity.ShipmentBagEntity;
import com.mertosi.deliverymanagement.model.entity.ShipmentBagEntityBuilder;
import com.mertosi.deliverymanagement.service.shipmentbag.ShipmentBagCommandService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = ShipmentBagCommandController.class)
class ShipmentBagCommandControllerTest extends AbstractControllerTest {
    @MockBean
    private ShipmentBagCommandService shipmentBagCommandService;

    private static final String SHIPMENT_BAG_ENDPOINT = "/api/v1/shipment-bag";

    @Test
    void givenValidShipmentBagRequest_whenCreateShipmentBagEntity_thenReturnShipmentResponse() {
        List<ShipmentBagRequest> shipmentBagRequests = ShipmentBagRequestBuilder.getValidShipmentBagRequests();
        List<ShipmentBagEntity> shipmentBagEntities = ShipmentBagEntityBuilder.getValidShipmentBagEntities();
        when(shipmentBagCommandService.create(anyList())).thenReturn(shipmentBagEntities);

        client.post()
                .uri(SHIPMENT_BAG_ENDPOINT)
                .bodyValue(shipmentBagRequests)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.errorMessage").isEmpty()
                .jsonPath("$.data").isNotEmpty();
    }
}
