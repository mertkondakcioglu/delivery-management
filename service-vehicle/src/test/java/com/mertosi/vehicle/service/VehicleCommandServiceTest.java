package com.mertosi.vehicle.service;

import com.mertosi.vehicle.model.dto.request.VehicleRequest;
import com.mertosi.vehicle.model.dto.request.VehicleRequestBuilder;
import com.mertosi.vehicle.model.entity.VehicleEntity;
import com.mertosi.vehicle.model.entity.VehicleEntityBuilder;
import com.mertosi.vehicle.repository.VehicleCommandRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class VehicleCommandServiceTest extends AbstractUnitTest {

    @InjectMocks
    VehicleCommandServiceImpl vehicleCommandService;

    @Mock
    private VehicleCommandRepository vehicleCommandRepository;

    @Test
    void givenValidVehicleRequest_whenCreateVehicleEntity_thenReturnVehicleEntity() {
        VehicleRequest testVehicleRequest = VehicleRequestBuilder.getValidVehicleRequest();
        VehicleEntity testVehicleEntity = VehicleEntityBuilder.getValidVehicleEntity();

        when(vehicleCommandRepository.save(any(VehicleEntity.class))).thenReturn(testVehicleEntity);
        VehicleEntity vehicleEntity = vehicleCommandService.create(testVehicleRequest);

        assertThat(testVehicleEntity).isEqualTo(vehicleEntity);
    }
}
