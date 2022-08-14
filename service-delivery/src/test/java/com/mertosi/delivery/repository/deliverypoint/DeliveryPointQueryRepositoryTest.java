package com.mertosi.delivery.repository.deliverypoint;

import com.mertosi.delivery.model.entity.DeliveryPointEntity;
import com.mertosi.delivery.repository.AbstractJpaIntegrationTest;
import com.mertosi.delivery.model.entity.DeliveryPointEntityBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class DeliveryPointQueryRepositoryTest extends AbstractJpaIntegrationTest {

    @Autowired
    private DeliveryPointQueryRepository deliveryPointQueryRepository;

    private DeliveryPointEntity testDeliveryPointEntity;

    @BeforeEach
    void setUp() {
        testDeliveryPointEntity = DeliveryPointEntityBuilder.getValidDeliveryPointEntityWithoutRelations().build();
        testEntityManager.persist(testDeliveryPointEntity);
    }

    @Test
    void givenValidValue_whenGetDeliveryPointEntity_thenReturnOptionalDeliveryPointEntity() {
        Optional<DeliveryPointEntity> deliveryPointEntity = deliveryPointQueryRepository.findByValue(testDeliveryPointEntity.getValue());
        assertThat(deliveryPointEntity).contains(testDeliveryPointEntity);
    }

    @Test
    void givenInvalidValue_whenGetDeliveryPointEntity_thenReturnNull() {
        Integer value = 4;
        Optional<DeliveryPointEntity> deliveryPointEntity = deliveryPointQueryRepository.findByValue(value);
        assertThat(deliveryPointEntity).isNotPresent();
    }
}
