package com.mertosi.vehicle.repository;

import com.mertosi.vehicle.model.entity.VehicleEntity;
import com.mertosi.vehicle.model.entity.VehicleEntityBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class VehicleQueryRepositoryTest extends AbstractJpaIntegrationTest {

    @Autowired
    private VehicleQueryRepository vehicleQueryRepository;

    private VehicleEntity testVehicleEntity;

    @BeforeEach
    void setUp() {
        testVehicleEntity = VehicleEntityBuilder.getValidVehicleEntityWithoutRelations().build();
        testEntityManager.persist(testVehicleEntity);
    }

    @Test
    void givenValidLicensePlate_whenGetVehicleEntity_thenReturnOptionalVehicleEntity() {
        Optional<VehicleEntity> vehicleEntity = vehicleQueryRepository.findByLicensePlate(testVehicleEntity.getLicensePlate());
        assertThat(vehicleEntity).contains(testVehicleEntity);
    }

    @Test
    void givenInvalidLicensePlate_whenGetVehicleEntity_thenReturnNull() {
        String licensePlate = "41 TR 41";
        Optional<VehicleEntity> vehicleEntity = vehicleQueryRepository.findByLicensePlate(licensePlate);
        assertThat(vehicleEntity).isNotPresent();
    }
}
