package com.mertosi.delivery.repository.shipmentbag;

import com.mertosi.delivery.model.entity.ShipmentBagEntity;
import com.mertosi.delivery.model.entity.ShipmentEntity;
import com.mertosi.delivery.repository.AbstractJpaIntegrationTest;
import com.mertosi.delivery.model.entity.ShipmentBagEntityBuilder;
import com.mertosi.delivery.model.entity.ShipmentEntityBuilder;
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
