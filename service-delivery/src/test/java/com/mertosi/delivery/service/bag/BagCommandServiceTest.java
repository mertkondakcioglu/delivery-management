package com.mertosi.delivery.service.bag;

import com.mertosi.delivery.common.enums.BagStatus;
import com.mertosi.delivery.common.exception.DeliveryException;
import com.mertosi.delivery.model.dto.request.BagRequest;
import com.mertosi.delivery.model.dto.request.BagRequestBuilder;
import com.mertosi.delivery.model.entity.BagEntity;
import com.mertosi.delivery.model.entity.BagEntityBuilder;
import com.mertosi.delivery.model.entity.DeliveryPointEntity;
import com.mertosi.delivery.model.entity.DeliveryPointEntityBuilder;
import com.mertosi.delivery.repository.bag.BagCommandRepository;
import com.mertosi.delivery.service.AbstractUnitTest;
import com.mertosi.delivery.service.deliverypoint.DeliveryPointQueryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class BagCommandServiceTest extends AbstractUnitTest {

    @InjectMocks
    BagCommandServiceImpl bagCommandService;

    @Mock
    private DeliveryPointQueryService deliveryPointQueryService;
    @Mock
    private BagCommandRepository bagCommandRepository;

    @Test
    void givenValidBagRequest_whenCreateBagEntity_thenReturnBagEntity() {
        BagRequest testBagRequest = BagRequestBuilder.getValidBagRequest();
        BagEntity testBagEntity = BagEntityBuilder.getValidBagEntity();
        DeliveryPointEntity testDeliveryPointEntity = DeliveryPointEntityBuilder.getValidDeliveryPointEntity();

        when(bagCommandRepository.save(any(BagEntity.class))).thenReturn(testBagEntity);
        when(deliveryPointQueryService.getByValue(any(Integer.class))).thenReturn(testDeliveryPointEntity);
        BagEntity bagEntity = bagCommandService.create(testBagRequest);

        assertThat(testBagEntity).isEqualTo(bagEntity);
    }

    @Test
    void givenValidStatus_whenUpdateBagEntity_thenReturnBagEntity() {
        BagStatus status = BagStatus.LOADED;
        BagEntity testBagEntity = BagEntityBuilder.getValidBagEntity();

        when(bagCommandRepository.save(any(BagEntity.class))).thenReturn(testBagEntity);
        BagEntity bagEntity = bagCommandService.updateStatus(testBagEntity, status);

        assertThat(testBagEntity).isEqualTo(bagEntity);
    }

    @Test
    void givenInvalidStatus_whenUpdateBagEntity_thenThrowDeliveryException() {
        BagStatus status = BagStatus.LOADED;
        BagEntity testBagEntity = BagEntityBuilder.getValidBagEntity();
        testBagEntity.setStatus(BagStatus.UNLOADED);

        assertThrows(DeliveryException.class, () -> bagCommandService.updateStatus(testBagEntity, status));
    }
}
