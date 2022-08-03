package com.mertosi.deliverymanagement.controller.deliverypoint;

import com.mertosi.deliverymanagement.common.exception.NotFoundException;
import com.mertosi.deliverymanagement.controller.AbstractControllerTest;
import com.mertosi.deliverymanagement.model.entity.DeliveryPointEntity;
import com.mertosi.deliverymanagement.model.entity.DeliveryPointEntityBuilder;
import com.mertosi.deliverymanagement.service.deliverypoint.DeliveryPointQueryService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import static org.mockito.Mockito.when;

@WebFluxTest(controllers = DeliveryPointQueryController.class)
class DeliveryPointQueryControllerTest extends AbstractControllerTest {
    @MockBean
    private DeliveryPointQueryService deliveryPointQueryService;

    private static final String GET_BY_VALUE_ENDPOINT = "/api/v1/delivery-point/{value}";

    @Test
    void givenValidValue_whenGetDeliveryPointEntity_thenReturnDeliveryPointResponse() {
        DeliveryPointEntity deliveryPointEntity = DeliveryPointEntityBuilder.getValidDeliveryPointEntity();
        when(deliveryPointQueryService.getByValue(deliveryPointEntity.getValue())).thenReturn(deliveryPointEntity);

        client.get()
                .uri(GET_BY_VALUE_ENDPOINT, deliveryPointEntity.getValue())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.errorMessage").isEmpty()
                .jsonPath("$.data").isNotEmpty();
    }

    @Test
    void givenInvalidValue_whenGetDeliveryPointEntity_thenThrowNotFoundException() {
        Integer value = 4;
        when(deliveryPointQueryService.getByValue(value)).thenThrow(new NotFoundException(value.toString()));

        client.get()
                .uri(GET_BY_VALUE_ENDPOINT, value)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_ACCEPTABLE)
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.success").isEqualTo(false)
                .jsonPath("$.errorMessage").isNotEmpty()
                .jsonPath("$.data").isEmpty();
    }
}
