package com.mertosi.vehicle.service;

import com.mertosi.vehicle.common.exception.NotFoundException;
import com.mertosi.vehicle.model.entity.VehicleEntity;
import com.mertosi.vehicle.model.entity.VehicleEntityBuilder;
import com.mertosi.vehicle.repository.VehicleQueryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class VehicleQueryServiceTest extends AbstractUnitTest {

    @InjectMocks
    VehicleQueryServiceImpl vehicleQueryService;

    @Mock
    private VehicleQueryRepository vehicleQueryRepository;

    @Test
    void givenValidLicensePlate_whenGetVehicleEntity_thenReturnVehicleEntity() {
        VehicleEntity testVehicleEntity = VehicleEntityBuilder.getValidVehicleEntity();
        when(vehicleQueryRepository.findByLicensePlate(testVehicleEntity.getLicensePlate())).thenReturn(Optional.of(testVehicleEntity));

        VehicleEntity vehicleEntity = vehicleQueryService.getByLicensePlate(testVehicleEntity.getLicensePlate());
        assertThat(testVehicleEntity).isEqualTo(vehicleEntity);
    }

    @Test
    void givenInvalidLicensePlate_whenGetVehicleEntity_thenThrowNotFoundException() {
        String licensePlate = "41 TR 41";
        assertThrows(NotFoundException.class, () -> vehicleQueryService.getByLicensePlate(licensePlate));
    }
}
