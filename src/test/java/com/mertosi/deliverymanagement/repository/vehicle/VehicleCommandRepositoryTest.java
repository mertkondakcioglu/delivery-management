package com.mertosi.deliverymanagement.repository.vehicle;

import com.mertosi.deliverymanagement.model.entity.VehicleEntity;
import com.mertosi.deliverymanagement.model.entity.VehicleEntityBuilder;
import com.mertosi.deliverymanagement.repository.AbstractJpaIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class VehicleCommandRepositoryTest extends AbstractJpaIntegrationTest {

    @Autowired
    private VehicleCommandRepository vehicleCommandRepository;

    private VehicleEntity testVehicleEntity;

    @BeforeEach
    void setUp() {
        testVehicleEntity = VehicleEntityBuilder.getValidVehicleEntityWithoutRelations().build();
    }

    @Test
    void givenValidVehicleEntity_whenCreateVehicleEntity_thenReturnVehicleEntity() {
        VehicleEntity savedVehicleEntity = vehicleCommandRepository.save(testVehicleEntity);
        Optional<VehicleEntity> vehicleEntity = vehicleCommandRepository.findById(savedVehicleEntity.getId());
        assertThat(vehicleEntity).contains(savedVehicleEntity);
    }
}
