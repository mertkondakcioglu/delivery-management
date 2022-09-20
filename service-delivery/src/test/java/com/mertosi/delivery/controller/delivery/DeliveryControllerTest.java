package com.mertosi.delivery.controller.delivery;

import com.mertosi.delivery.controller.AbstractControllerTest;
import com.mertosi.delivery.model.dto.request.delivery.MakeDeliveryRequest;
import com.mertosi.delivery.model.dto.request.delivery.MakeDeliveryRequestBuilder;
import com.mertosi.delivery.model.dto.response.delivery.MakeDeliveryResponse;
import com.mertosi.delivery.model.dto.response.delivery.MakeDeliveryResponseBuilder;
import com.mertosi.delivery.service.delivery.MakeDeliveryService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = DeliveryController.class)
class DeliveryControllerTest extends AbstractControllerTest {
    @MockBean
    private MakeDeliveryService makeDeliveryService;

    private static final String DELIVERY_ENDPOINT = "/api/v1/delivery";

    @Test
    void givenValidMakeDeliveryRequest_whenMakeDelivery_thenReturnMakeDeliveryResponse() {
        MakeDeliveryRequest makeDeliveryRequest = MakeDeliveryRequestBuilder.getValidMakeDeliveryRequest();
        MakeDeliveryResponse makeDeliveryResponse = MakeDeliveryResponseBuilder.getValidMakeDeliveryResponse();
        when(makeDeliveryService.makeDelivery(any(MakeDeliveryRequest.class))).thenReturn(makeDeliveryResponse);

        client.post()
                .uri(DELIVERY_ENDPOINT)
                .bodyValue(makeDeliveryRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.errorMessage").isEmpty()
                .jsonPath("$.data").isNotEmpty();
    }
}
