package com.mertosi.delivery.service.deliverypoint;

import com.mertosi.delivery.model.dto.request.DeliveryPointRequest;
import com.mertosi.delivery.model.dto.request.DeliveryPointRequestBuilder;
import com.mertosi.delivery.model.entity.DeliveryPointEntity;
import com.mertosi.delivery.model.entity.DeliveryPointEntityBuilder;
import com.mertosi.delivery.repository.deliverypoint.DeliveryPointCommandRepository;
import com.mertosi.delivery.service.AbstractUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class DeliveryPointCommandServiceTest extends AbstractUnitTest {

    @InjectMocks
    DeliveryPointCommandServiceImpl deliveryPointCommandService;

    @Mock
    private DeliveryPointCommandRepository deliveryPointCommandRepository;

    @Test
    void givenValidDeliveryPointRequest_whenCreateDeliveryPointEntity_thenReturnDeliveryPointEntity() {
        DeliveryPointRequest testDeliveryPointRequest = DeliveryPointRequestBuilder.getValidDeliveryPointRequest();
        DeliveryPointEntity testDeliveryPointEntity = DeliveryPointEntityBuilder.getValidDeliveryPointEntity();

        when(deliveryPointCommandRepository.save(any(DeliveryPointEntity.class))).thenReturn(testDeliveryPointEntity);
        DeliveryPointEntity deliveryPointEntity = deliveryPointCommandService.create(testDeliveryPointRequest);

        assertThat(testDeliveryPointEntity).isEqualTo(deliveryPointEntity);
    }
}
