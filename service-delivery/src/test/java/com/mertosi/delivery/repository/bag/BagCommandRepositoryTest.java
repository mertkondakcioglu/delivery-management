package com.mertosi.delivery.repository.bag;

import com.mertosi.delivery.common.enums.BagStatus;
import com.mertosi.delivery.model.entity.BagEntity;
import com.mertosi.delivery.model.entity.BagEntityBuilder;
import com.mertosi.delivery.repository.AbstractJpaIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class BagCommandRepositoryTest extends AbstractJpaIntegrationTest {

    @Autowired
    private BagCommandRepository bagCommandRepository;

    private BagEntity testBagEntity;

    @BeforeEach
    void setUp() {
        testBagEntity = BagEntityBuilder.getValidBagEntityWithoutRelations().build();
    }

    @Test
    void givenValidBagEntity_whenCreateBagEntity_thenReturnBagEntity() {
        BagEntity savedBagEntity = bagCommandRepository.save(testBagEntity);
        Optional<BagEntity> bagEntity = bagCommandRepository.findById(savedBagEntity.getId());
        assertThat(bagEntity).contains(savedBagEntity);
    }

    @Test
    void givenValidStatus_whenUpdateBagEntity_thenReturnBagEntity() {
        testEntityManager.persist(testBagEntity);
        testBagEntity.setStatus(BagStatus.LOADED);
        BagEntity updatedBagEntity = bagCommandRepository.save(testBagEntity);

        Optional<BagEntity> bagEntity = bagCommandRepository.findById(testBagEntity.getId());
        assertThat(bagEntity).contains(updatedBagEntity);
    }
}
