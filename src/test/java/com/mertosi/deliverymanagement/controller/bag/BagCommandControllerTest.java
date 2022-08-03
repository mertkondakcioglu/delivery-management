package com.mertosi.deliverymanagement.controller.bag;

import com.mertosi.deliverymanagement.controller.AbstractControllerTest;
import com.mertosi.deliverymanagement.model.dto.request.BagRequest;
import com.mertosi.deliverymanagement.model.dto.request.BagRequestBuilder;
import com.mertosi.deliverymanagement.model.entity.BagEntity;
import com.mertosi.deliverymanagement.model.entity.BagEntityBuilder;
import com.mertosi.deliverymanagement.service.bag.BagCommandService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = BagCommandController.class)
class BagCommandControllerTest extends AbstractControllerTest {
    @MockBean
    private BagCommandService bagCommandService;

    private static final String BAG_ENDPOINT = "/api/v1/bag";

    @Test
    void givenValidBagRequest_whenCreateBagEntity_thenReturnBagResponse() {
        BagRequest bagRequest = BagRequestBuilder.getValidBagRequest();
        BagEntity bagEntity = BagEntityBuilder.getValidBagEntity();
        when(bagCommandService.create(any(BagRequest.class))).thenReturn(bagEntity);

        client.post()
                .uri(BAG_ENDPOINT)
                .bodyValue(bagRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.errorMessage").isEmpty()
                .jsonPath("$.data").isNotEmpty();
    }
}
