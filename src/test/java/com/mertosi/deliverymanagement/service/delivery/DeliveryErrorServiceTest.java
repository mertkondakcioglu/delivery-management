package com.mertosi.deliverymanagement.service.delivery;

import com.mertosi.deliverymanagement.model.entity.DeliveryErrorEntity;
import com.mertosi.deliverymanagement.model.entity.DeliveryErrorEntityBuilder;
import com.mertosi.deliverymanagement.repository.delivery.DeliveryErrorRepository;
import com.mertosi.deliverymanagement.service.AbstractUnitTest;
import com.mertosi.deliverymanagement.service.delivery.error.DeliveryErrorServiceImpl;
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
