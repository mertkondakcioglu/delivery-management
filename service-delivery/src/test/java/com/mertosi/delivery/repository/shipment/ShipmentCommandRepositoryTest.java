package com.mertosi.delivery.repository.shipment;

import com.mertosi.delivery.common.enums.ShipmentStatus;
import com.mertosi.delivery.model.entity.ShipmentEntity;
import com.mertosi.delivery.model.entity.ShipmentEntityBuilder;
import com.mertosi.delivery.repository.AbstractJpaIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ShipmentCommandRepositoryTest extends AbstractJpaIntegrationTest {

    @Autowired
    private ShipmentCommandRepository shipmentCommandRepository;

    private ShipmentEntity testShipmentEntity;

    @BeforeEach
    void setUp() {
        testShipmentEntity = ShipmentEntityBuilder.getValidShipmentEntityWithoutRelations().build();
    }

    @Test
    void givenValidShipmentEntity_whenCreateShipmentEntity_thenReturnShipmentEntity() {
        ShipmentEntity savedShipmentEntity = shipmentCommandRepository.save(testShipmentEntity);
        Optional<ShipmentEntity> shipmentEntity = shipmentCommandRepository.findById(savedShipmentEntity.getId());
        assertThat(shipmentEntity).contains(savedShipmentEntity);
    }

    @Test
    void givenValidStatus_whenUpdateShipmentEntity_thenReturnShipmentEntity() {
        testEntityManager.persist(testShipmentEntity);
        testShipmentEntity.setStatus(ShipmentStatus.LOADED);
        ShipmentEntity updatedShipmentEntity = shipmentCommandRepository.save(testShipmentEntity);

        Optional<ShipmentEntity> shipmentEntity = shipmentCommandRepository.findById(testShipmentEntity.getId());
        assertThat(shipmentEntity).contains(updatedShipmentEntity);
    }
}
