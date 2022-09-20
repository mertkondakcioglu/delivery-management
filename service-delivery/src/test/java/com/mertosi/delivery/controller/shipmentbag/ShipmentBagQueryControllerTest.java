package com.mertosi.delivery.controller.shipmentbag;

import com.mertosi.delivery.controller.AbstractControllerTest;
import com.mertosi.delivery.model.entity.ShipmentBagEntity;
import com.mertosi.delivery.model.entity.ShipmentBagEntityBuilder;
import com.mertosi.delivery.service.shipmentbag.ShipmentBagQueryService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.when;

@WebFluxTest(controllers = ShipmentBagQueryController.class)
class ShipmentBagQueryControllerTest extends AbstractControllerTest {
    @MockBean
    private ShipmentBagQueryService shipmentBagQueryService;

    private static final String SHIPMENT_BAG_ENDPOINT = "/api/v1/shipment-bag";


    @Test
    void happyPath_getAll() {
        List<ShipmentBagEntity> shipmentBagEntities = ShipmentBagEntityBuilder.getValidShipmentBagEntities();
        when(shipmentBagQueryService.getAll()).thenReturn(shipmentBagEntities);

        client.get()
                .uri(SHIPMENT_BAG_ENDPOINT)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.errorMessage").isEmpty()
                .jsonPath("$.data").isNotEmpty();
    }
}
