package com.mertosi.deliverymanagement.service.bag;

import com.mertosi.deliverymanagement.common.enums.BagStatus;
import com.mertosi.deliverymanagement.common.exception.NotFoundException;
import com.mertosi.deliverymanagement.model.entity.BagEntity;
import com.mertosi.deliverymanagement.model.entity.BagEntityBuilder;
import com.mertosi.deliverymanagement.repository.bag.BagQueryRepository;
import com.mertosi.deliverymanagement.service.AbstractUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class BagQueryServiceTest extends AbstractUnitTest {

    @InjectMocks
    BagQueryServiceImpl bagQueryService;

    @Mock
    private BagQueryRepository bagQueryRepository;

    @Test
    void givenValidBarcode_whenGetBagEntity_thenReturnBagEntity() {
        BagEntity testBagEntity = BagEntityBuilder.getValidBagEntity();
        when(bagQueryRepository.findByBarcode(testBagEntity.getBarcode())).thenReturn(Optional.of(testBagEntity));

        BagEntity bagEntity = bagQueryService.getByBarcode(testBagEntity.getBarcode());
        assertThat(testBagEntity).isEqualTo(bagEntity);
    }

    @Test
    void givenInvalidBarcode_whenGetBagEntity_thenThrowNotFoundException() {
        String barcode = "C123456";
        assertThrows(NotFoundException.class, () -> bagQueryService.getByBarcode(barcode));
    }

    @Test
    void givenValidStatus_whenGetBagEntities_thenReturnBagEntities() {
        BagStatus status = BagStatus.CREATED;
        List<BagEntity> testBagEntities = BagEntityBuilder.getValidBagEntities();
        when(bagQueryRepository.findAllByStatus(status)).thenReturn(testBagEntities);

        List<BagEntity> bagEntities = bagQueryService.getAllByStatus(status);
        assertThat(testBagEntities).isEqualTo(bagEntities);
    }
}
