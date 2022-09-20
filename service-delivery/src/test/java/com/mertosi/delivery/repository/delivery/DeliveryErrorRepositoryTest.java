package com.mertosi.delivery.repository.delivery;

import com.mertosi.delivery.model.entity.DeliveryErrorEntity;
import com.mertosi.delivery.model.entity.DeliveryErrorEntityBuilder;
import com.mertosi.delivery.repository.AbstractJpaIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class DeliveryErrorRepositoryTest extends AbstractJpaIntegrationTest {

    @Autowired
    private DeliveryErrorRepository deliveryErrorRepository;

    private DeliveryErrorEntity testDeliveryErrorEntity;

    @BeforeEach
    void setUp() {
        testDeliveryErrorEntity = DeliveryErrorEntityBuilder.getValidDeliveryErrorEntityWithoutRelations().build();
    }

    @Test
    void givenValidDeliveryErrorEntity_whenCreateDeliveryErrorEntity_thenReturnDeliveryErrorEntity() {
        DeliveryErrorEntity savedDeliveryErrorEntity = deliveryErrorRepository.save(testDeliveryErrorEntity);
        Optional<DeliveryErrorEntity> deliveryErrorEntity = deliveryErrorRepository.findById(savedDeliveryErrorEntity.getId());
        assertThat(deliveryErrorEntity).contains(savedDeliveryErrorEntity);
    }
}
