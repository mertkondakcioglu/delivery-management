package com.mertosi.deliverymanagement.repository.shipmentbag;

import com.mertosi.deliverymanagement.model.entity.ShipmentBagEntity;
import com.mertosi.deliverymanagement.model.entity.ShipmentBagEntityBuilder;
import com.mertosi.deliverymanagement.model.entity.ShipmentEntity;
import com.mertosi.deliverymanagement.model.entity.ShipmentEntityBuilder;
import com.mertosi.deliverymanagement.repository.AbstractJpaIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ShipmentBagQueryRepositoryTest extends AbstractJpaIntegrationTest {

    @Autowired
    private ShipmentBagQueryRepository shipmentBagQueryRepository;

    private ShipmentBagEntity testShipmentBagEntity;

    @BeforeEach
    void setUp() {
        testShipmentBagEntity = ShipmentBagEntityBuilder.getValidShipmentBagEntityWithoutRelations().build();
        testEntityManager.persist(testShipmentBagEntity);
    }

    @Test
    void givenValidBarcode_whenGetShipmentBagEntity_thenReturnShipmentBagEntity() {
        ShipmentBagEntity shipmentBagEntity = shipmentBagQueryRepository.findByBarcode(testShipmentBagEntity.getBarcode());
        assertThat(shipmentBagEntity).isEqualTo(testShipmentBagEntity);
    }

    @Test
    void givenInvalidBarcode_whenGetShipmentBagEntity_thenReturnNull() {
        ShipmentEntity testShipmentEntity = ShipmentEntityBuilder.getValidShipmentEntityWithoutRelations().build();
        ShipmentBagEntity shipmentBagEntity = shipmentBagQueryRepository.findByBarcode(testShipmentEntity);
        assertThat(shipmentBagEntity).isNull();
    }

    @Test
    void happyPath_getAll() {
        List<ShipmentBagEntity> shipmentBagEntities = shipmentBagQueryRepository.findAll();
        assertThat(shipmentBagEntities).contains(testShipmentBagEntity);
    }
}
