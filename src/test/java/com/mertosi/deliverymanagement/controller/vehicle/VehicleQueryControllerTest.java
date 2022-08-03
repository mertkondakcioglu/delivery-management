package com.mertosi.deliverymanagement.controller.vehicle;

import com.mertosi.deliverymanagement.common.exception.NotFoundException;
import com.mertosi.deliverymanagement.controller.AbstractControllerTest;
import com.mertosi.deliverymanagement.model.entity.VehicleEntity;
import com.mertosi.deliverymanagement.model.entity.VehicleEntityBuilder;
import com.mertosi.deliverymanagement.service.vehicle.VehicleQueryService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import static org.mockito.Mockito.when;

@WebFluxTest(controllers = VehicleQueryController.class)
class VehicleQueryControllerTest extends AbstractControllerTest {
    @MockBean
    private VehicleQueryService vehicleQueryService;

    private static final String GET_BY_LICENSE_PLATE_ENDPOINT = "/api/v1/vehicle/{licensePlate}";

    @Test
    void givenValidLicensePlate_whenGetVehicleEntity_thenReturnVehicleResponse() {
        VehicleEntity vehicleEntity = VehicleEntityBuilder.getValidVehicleEntity();
        when(vehicleQueryService.getByLicensePlate(vehicleEntity.getLicensePlate())).thenReturn(vehicleEntity);

        client.get()
                .uri(GET_BY_LICENSE_PLATE_ENDPOINT, vehicleEntity.getLicensePlate())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.success").isEqualTo(true)
                .jsonPath("$.errorMessage").isEmpty()
                .jsonPath("$.data").isNotEmpty();
    }

    @Test
    void givenInvalidLicensePlate_whenGetVehicleEntity_thenThrowNotFoundException() {
        String licensePlate = "41 TR 41";
        when(vehicleQueryService.getByLicensePlate(licensePlate)).thenThrow(new NotFoundException(licensePlate));

        client.get()
                .uri(GET_BY_LICENSE_PLATE_ENDPOINT, licensePlate)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_ACCEPTABLE)
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.success").isEqualTo(false)
                .jsonPath("$.errorMessage").isNotEmpty()
                .jsonPath("$.data").isEmpty();
    }
}
