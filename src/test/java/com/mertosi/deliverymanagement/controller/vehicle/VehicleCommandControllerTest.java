package com.mertosi.deliverymanagement.controller.vehicle;

import com.mertosi.deliverymanagement.controller.AbstractControllerTest;
import com.mertosi.deliverymanagement.model.dto.request.VehicleRequest;
import com.mertosi.deliverymanagement.model.dto.request.VehicleRequestBuilder;
import com.mertosi.deliverymanagement.model.entity.VehicleEntity;
import com.mertosi.deliverymanagement.model.entity.VehicleEntityBuilder;
import com.mertosi.deliverymanagement.service.vehicle.VehicleCommandService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(controllers = VehicleCommandController.class)
class VehicleCommandControllerTest extends AbstractControllerTest {
    @MockBean
    private VehicleCommandService vehicleCommandService;

    private static final String VEHICLE_ENDPOINT = "/api/v1/vehicle";

    @Test
    void givenValidVehicleRequest_whenCreateVehicleEntity_thenReturnVehicleResponse() {
        VehicleRequest vehicleRequest = VehicleRequestBuilder.getValidVehicleRequest();
        VehicleEntity vehicleEntity = VehicleEntityBuilder.getValidVehicleEntity();
        when(vehicleCommandService.create(any(VehicleRequest.class))).thenReturn(vehicleEntity);

        client.post()
                .uri(VEHICLE_ENDPOINT)
                .bodyValue(vehicleRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.errorMessage").isEmpty()
                .jsonPath("$.data").isNotEmpty();
    }
}
