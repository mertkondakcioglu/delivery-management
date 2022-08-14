package com.mertosi.delivery.repository.bag;

import com.mertosi.delivery.common.enums.BagStatus;
import com.mertosi.delivery.model.entity.BagEntity;
import com.mertosi.delivery.repository.AbstractJpaIntegrationTest;
import com.mertosi.delivery.model.entity.BagEntityBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class BagQueryRepositoryTest extends AbstractJpaIntegrationTest {

    @Autowired
    private BagQueryRepository bagQueryRepository;

    private BagEntity testBagEntity;

    @BeforeEach
    void setUp() {
        testBagEntity = BagEntityBuilder.getValidBagEntityWithoutRelations().build();
        testEntityManager.persist(testBagEntity);
    }

    @Test
    void givenValidBarcode_whenGetBagEntity_thenReturnOptionalBagEntity() {
        Optional<BagEntity> bagEntity = bagQueryRepository.findByBarcode(testBagEntity.getBarcode());
        assertThat(bagEntity).contains(testBagEntity);
    }

    @Test
    void givenInvalidBarcode_whenGetBagEntity_thenReturnNull() {
        String barcode = "C123456";
        Optional<BagEntity> bagEntity = bagQueryRepository.findByBarcode(barcode);
        assertThat(bagEntity).isNotPresent();
    }

    @Test
    void givenValidStatus_whenGetBagEntities_thenReturnBagEntities() {
        BagStatus status = BagStatus.CREATED;
        List<BagEntity> bagEntities = bagQueryRepository.findAllByStatus(status);
        assertThat(bagEntities).contains(testBagEntity);
    }
}
