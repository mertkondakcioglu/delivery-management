package com.mertosi.delivery.service.delivery;

import com.mertosi.delivery.model.entity.DeliveryErrorEntity;
import com.mertosi.delivery.model.entity.DeliveryErrorEntityBuilder;
import com.mertosi.delivery.repository.delivery.DeliveryErrorRepository;
import com.mertosi.delivery.service.AbstractUnitTest;
import com.mertosi.delivery.service.delivery.error.DeliveryErrorServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class DeliveryErrorServiceTest extends AbstractUnitTest {

    @InjectMocks
    DeliveryErrorServiceImpl deliveryErrorService;

    @Mock
    private DeliveryErrorRepository deliveryErrorRepository;

    @Test
    void givenValidDeliveryErrorRequest_whenCreateDeliveryErrorEntity_thenReturnDeliveryErrorEntity() {
        DeliveryErrorEntity testDeliveryErrorEntity = DeliveryErrorEntityBuilder.getValidDeliveryErrorEntity();

        when(deliveryErrorRepository.save(any(DeliveryErrorEntity.class))).thenReturn(testDeliveryErrorEntity);
        DeliveryErrorEntity vehicleEntity = deliveryErrorService.create(testDeliveryErrorEntity.getBarcode(), testDeliveryErrorEntity.getDeliveryPoint());

        assertThat(testDeliveryErrorEntity).isEqualTo(vehicleEntity);
    }
}
