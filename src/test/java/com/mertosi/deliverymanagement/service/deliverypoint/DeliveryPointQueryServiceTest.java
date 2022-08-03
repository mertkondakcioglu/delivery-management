package com.mertosi.deliverymanagement.service.deliverypoint;

import com.mertosi.deliverymanagement.common.exception.NotFoundException;
import com.mertosi.deliverymanagement.model.entity.DeliveryPointEntity;
import com.mertosi.deliverymanagement.model.entity.DeliveryPointEntityBuilder;
import com.mertosi.deliverymanagement.repository.deliverypoint.DeliveryPointQueryRepository;
import com.mertosi.deliverymanagement.service.AbstractUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class DeliveryPointQueryServiceTest extends AbstractUnitTest {

    @InjectMocks
    DeliveryPointQueryServiceImpl deliveryPointQueryService;

    @Mock
    private DeliveryPointQueryRepository deliveryPointQueryRepository;

    @Test
    void givenValidValue_whenGetDeliveryPointEntity_thenReturnDeliveryPointEntity() {
        DeliveryPointEntity testDeliveryPointEntity = DeliveryPointEntityBuilder.getValidDeliveryPointEntity();
        when(deliveryPointQueryRepository.findByValue(testDeliveryPointEntity.getValue())).thenReturn(Optional.of(testDeliveryPointEntity));

        DeliveryPointEntity deliveryPointEntity = deliveryPointQueryService.getByValue(testDeliveryPointEntity.getValue());
        assertThat(testDeliveryPointEntity).isEqualTo(deliveryPointEntity);
    }

    @Test
    void givenInvalidValue_whenGetDeliveryPointEntity_thenThrowNotFoundException() {
        Integer value = 4;
        assertThrows(NotFoundException.class, () -> deliveryPointQueryService.getByValue(value));
    }
}
