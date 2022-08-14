package com.mertosi.delivery.repository.shipmentbag;

import com.mertosi.delivery.model.entity.ShipmentBagEntity;
import com.mertosi.delivery.repository.AbstractJpaIntegrationTest;
import com.mertosi.delivery.model.entity.ShipmentBagEntityBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ShipmentBagCommandRepositoryTest extends AbstractJpaIntegrationTest {

    @Autowired
    private ShipmentBagCommandRepository shipmentBagCommandRepository;

    private ShipmentBagEntity testShipmentBagEntity;

    @BeforeEach
    void setUp() {
        testShipmentBagEntity = ShipmentBagEntityBuilder.getValidShipmentBagEntityWithoutRelations().build();
    }

    @Test
    void givenValidShipmentBagEntity_whenCreateShipmentBagEntity_thenReturnShipmentBagEntity() {
        ShipmentBagEntity savedShipmentBagEntity = shipmentBagCommandRepository.save(testShipmentBagEntity);
        Optional<ShipmentBagEntity> shipmentBagEntity = shipmentBagCommandRepository.findById(savedShipmentBagEntity.getId());
        assertThat(shipmentBagEntity).contains(savedShipmentBagEntity);
    }
}
