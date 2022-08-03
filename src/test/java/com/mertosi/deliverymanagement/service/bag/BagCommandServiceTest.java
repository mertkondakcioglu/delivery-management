package com.mertosi.deliverymanagement.service.bag;

import com.mertosi.deliverymanagement.common.enums.BagStatus;
import com.mertosi.deliverymanagement.common.exception.DeliveryManagementException;
import com.mertosi.deliverymanagement.model.dto.request.BagRequest;
import com.mertosi.deliverymanagement.model.dto.request.BagRequestBuilder;
import com.mertosi.deliverymanagement.model.entity.BagEntity;
import com.mertosi.deliverymanagement.model.entity.BagEntityBuilder;
import com.mertosi.deliverymanagement.model.entity.DeliveryPointEntity;
import com.mertosi.deliverymanagement.model.entity.DeliveryPointEntityBuilder;
import com.mertosi.deliverymanagement.repository.bag.BagCommandRepository;
import com.mertosi.deliverymanagement.service.AbstractUnitTest;
import com.mertosi.deliverymanagement.service.deliverypoint.DeliveryPointQueryService;
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
    void givenInvalidStatus_whenUpdateBagEntity_thenThrowDeliveryManagementException() {
        BagStatus status = BagStatus.LOADED;
        BagEntity testBagEntity = BagEntityBuilder.getValidBagEntity();
        testBagEntity.setStatus(BagStatus.UNLOADED);

        assertThrows(DeliveryManagementException.class, () -> bagCommandService.updateStatus(testBagEntity, status));
    }
}
