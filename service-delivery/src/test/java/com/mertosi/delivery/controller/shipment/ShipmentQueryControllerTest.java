package com.mertosi.delivery.controller.shipment;

import com.mertosi.delivery.common.exception.NotFoundException;
import com.mertosi.delivery.controller.AbstractControllerTest;
import com.mertosi.delivery.model.entity.ShipmentEntity;
import com.mertosi.delivery.model.entity.ShipmentEntityBuilder;
import com.mertosi.delivery.service.shipment.ShipmentQueryService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.mockito.Mockito.when;

@WebFluxTest(controllers = ShipmentQueryController.class)
class ShipmentQueryControllerTest extends AbstractControllerTest {
    @MockBean
    private ShipmentQueryService shipmentQueryService;

    private static final String SHIPMENT_ENDPOINT = "/api/v1/shipment";
    private static final String GET_BY_BARCODE_ENDPOINT = SHIPMENT_ENDPOINT + "/{barcode}";

    @Test
    void givenValidBarcode_whenGetShipmentEntity_thenReturnShipmentResponse() {
        ShipmentEntity shipmentEntity = ShipmentEntityBuilder.getValidShipmentEntity();
        when(shipmentQueryService.getByBarcode(shipmentEntity.getBarcode())).thenReturn(shipmentEntity);

        client.get()
                .uri(GET_BY_BARCODE_ENDPOINT, shipmentEntity.getBarcode())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.errorMessage").isEmpty()
                .jsonPath("$.data").isNotEmpty();
    }

    @Test
    void givenInvalidBarcode_whenGetShipmentEntity_thenThrowNotFoundException() {
        String barcode = "P1234567890";
        when(shipmentQueryService.getByBarcode(barcode)).thenThrow(new NotFoundException(barcode));

        client.get()
                .uri(GET_BY_BARCODE_ENDPOINT, barcode)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_ACCEPTABLE)
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.success").isEqualTo(false)
                .jsonPath("$.errorMessage").isNotEmpty()
                .jsonPath("$.data").isEmpty();
    }

    @Test
    void happyPath_getAll() {
        List<ShipmentEntity> shipmentEntities = ShipmentEntityBuilder.getValidShipmentEntities();
        when(shipmentQueryService.getAll()).thenReturn(shipmentEntities);

        client.get()
                .uri(SHIPMENT_ENDPOINT)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.errorMessage").isEmpty()
                .jsonPath("$.data").isNotEmpty();
    }
}
