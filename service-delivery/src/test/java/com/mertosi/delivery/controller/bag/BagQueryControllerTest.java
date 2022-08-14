package com.mertosi.delivery.controller.bag;

import com.mertosi.delivery.common.exception.NotFoundException;
import com.mertosi.delivery.controller.AbstractControllerTest;
import com.mertosi.delivery.model.entity.BagEntity;
import com.mertosi.delivery.service.bag.BagQueryService;
import com.mertosi.delivery.model.entity.BagEntityBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import static org.mockito.Mockito.when;

@WebFluxTest(controllers = BagQueryController.class)
class BagQueryControllerTest extends AbstractControllerTest {
    @MockBean
    private BagQueryService bagQueryService;

    private static final String GET_BY_BARCODE_ENDPOINT = "/api/v1/bag/{barcode}";

    @Test
    void givenValidBarcode_whenGetBagEntity_thenReturnBagResponse() {
        BagEntity bagEntity = BagEntityBuilder.getValidBagEntity();
        when(bagQueryService.getByBarcode(bagEntity.getBarcode())).thenReturn(bagEntity);

        client.get()
                .uri(GET_BY_BARCODE_ENDPOINT, bagEntity.getBarcode())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.errorMessage").isEmpty()
                .jsonPath("$.data").isNotEmpty();
    }

    @Test
    void givenInvalidBarcode_whenGetBagEntity_thenThrowNotFoundException() {
        String barcode = "C123456";
        when(bagQueryService.getByBarcode(barcode)).thenThrow(new NotFoundException(barcode));

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
}
