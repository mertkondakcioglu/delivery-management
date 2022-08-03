package com.mertosi.deliverymanagement.repository.shipmentbag;

import com.mertosi.deliverymanagement.model.entity.ShipmentBagEntity;
import com.mertosi.deliverymanagement.model.entity.ShipmentBagEntityBuilder;
import com.mertosi.deliverymanagement.repository.AbstractJpaIntegrationTest;
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
