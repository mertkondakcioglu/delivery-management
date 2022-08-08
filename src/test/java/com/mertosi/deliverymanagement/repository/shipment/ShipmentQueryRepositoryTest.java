package com.mertosi.deliverymanagement.repository.shipment;

import com.mertosi.deliverymanagement.model.entity.*;
import com.mertosi.deliverymanagement.repository.AbstractJpaIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ShipmentQueryRepositoryTest extends AbstractJpaIntegrationTest {

    @Autowired
    private ShipmentQueryRepository shipmentQueryRepository;

    private ShipmentEntity testShipmentEntity;

    @BeforeEach
    void setUp() {
        testShipmentEntity = ShipmentEntityBuilder.getValidShipmentEntityWithoutRelations().build();
        testEntityManager.persist(testShipmentEntity);
    }

    @Test
    void givenValidBarcode_whenGetShipmentEntity_thenReturnOptionalShipmentEntity() {
        Optional<ShipmentEntity> shipmentEntity = shipmentQueryRepository.findByBarcode(testShipmentEntity.getBarcode());
        assertThat(shipmentEntity).contains(testShipmentEntity);
    }

    @Test
    void givenInvalidBarcode_whenGetShipmentEntity_thenReturnNull() {
        String barcode = "P1234567980";
        Optional<ShipmentEntity> shipmentEntity = shipmentQueryRepository.findByBarcode(barcode);
        assertThat(shipmentEntity).isNotPresent();
    }

    @Test
    void happyPath_getAll() {
        List<ShipmentEntity> shipmentEntities = shipmentQueryRepository.findAll();
        assertThat(shipmentEntities).contains(testShipmentEntity);
    }

    @Test
    void givenValidBagBarcode_whenGetShipmentEntities_thenReturnShipmentEntities() {
        BagEntity testBagEntity = BagEntityBuilder.getValidBagEntityWithoutRelations().build();
        testEntityManager.persist(testBagEntity);

        ShipmentBagEntity testShipmentBagEntity = ShipmentBagEntityBuilder.getValidShipmentBagEntityWithoutRelations()
                .withBarcode(testShipmentEntity)
                .withBagBarcode(testBagEntity)
                .build();
        testEntityManager.persist(testShipmentBagEntity);

        List<ShipmentEntity> shipmentEntities = shipmentQueryRepository.findShipmentsInBagByBagBarcode(testBagEntity.getBarcode());
        assertThat(shipmentEntities).contains(testShipmentEntity);
    }
}
