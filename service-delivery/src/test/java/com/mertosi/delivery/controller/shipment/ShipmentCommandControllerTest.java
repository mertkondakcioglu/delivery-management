package com.mertosi.delivery.controller.shipment;

import com.mertosi.delivery.model.dto.request.ShipmentRequest;
import com.mertosi.delivery.model.entity.ShipmentEntity;
import com.mertosi.delivery.service.shipment.ShipmentCommandService;
import com.mertosi.delivery.controller.AbstractControllerTest;
import com.mertosi.delivery.model.dto.request.ShipmentRequestBuilder;
import com.mertosi.delivery.model.entity.ShipmentEntityBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = ShipmentCommandController.class)
class ShipmentCommandControllerTest extends AbstractControllerTest {
    @MockBean
    private ShipmentCommandService shipmentCommandService;

    private static final String SHIPMENT_ENDPOINT = "/api/v1/shipment";

    @Test
    void givenValidShipmentRequest_whenCreateShipmentEntity_thenReturnShipmentResponse() {
        List<ShipmentRequest> shipmentRequests = ShipmentRequestBuilder.getValidShipmentRequests();
        List<ShipmentEntity> shipmentEntities = ShipmentEntityBuilder.getValidShipmentEntities();
        when(shipmentCommandService.create(anyList())).thenReturn(shipmentEntities);

        client.post()
                .uri(SHIPMENT_ENDPOINT)
                .bodyValue(shipmentRequests)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.errorMessage").isEmpty()
                .jsonPath("$.data").isNotEmpty();
    }
}
