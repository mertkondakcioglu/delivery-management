package com.mertosi.delivery.controller.deliverypoint;

import com.mertosi.delivery.controller.AbstractControllerTest;
import com.mertosi.delivery.model.dto.request.DeliveryPointRequest;
import com.mertosi.delivery.model.dto.request.DeliveryPointRequestBuilder;
import com.mertosi.delivery.model.entity.DeliveryPointEntity;
import com.mertosi.delivery.model.entity.DeliveryPointEntityBuilder;
import com.mertosi.delivery.service.deliverypoint.DeliveryPointCommandService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = DeliveryPointCommandController.class)
class DeliveryPointCommandControllerTest extends AbstractControllerTest {
    @MockBean
    private DeliveryPointCommandService deliveryPointCommandService;

    private static final String DELIVERY_POINT_ENDPOINT = "/api/v1/delivery-point";

    @Test
    void givenValidDeliveryPointRequest_whenCreateDeliveryPointEntity_thenReturnDeliveryPointResponse() {
        DeliveryPointRequest deliveryPointRequest = DeliveryPointRequestBuilder.getValidDeliveryPointRequest();
        DeliveryPointEntity deliveryPointEntity = DeliveryPointEntityBuilder.getValidDeliveryPointEntity();
        when(deliveryPointCommandService.create(any(DeliveryPointRequest.class))).thenReturn(deliveryPointEntity);

        client.post()
                .uri(DELIVERY_POINT_ENDPOINT)
                .bodyValue(deliveryPointRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.errorMessage").isEmpty()
                .jsonPath("$.data").isNotEmpty();
    }
}
