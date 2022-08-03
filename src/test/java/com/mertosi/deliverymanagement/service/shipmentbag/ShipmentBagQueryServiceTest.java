package com.mertosi.deliverymanagement.service.shipmentbag;

import com.mertosi.deliverymanagement.model.entity.ShipmentBagEntity;
import com.mertosi.deliverymanagement.model.entity.ShipmentBagEntityBuilder;
import com.mertosi.deliverymanagement.repository.shipmentbag.ShipmentBagQueryRepository;
import com.mertosi.deliverymanagement.service.AbstractUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class ShipmentBagQueryServiceTest extends AbstractUnitTest {

    @InjectMocks
    ShipmentBagQueryServiceImpl shipmentBagQueryService;

    @Mock
    private ShipmentBagQueryRepository shipmentBagQueryRepository;

    @Test
    void givenValidBarcode_whenGetShipmentBagEntity_thenReturnShipmentBagEntity() {
        ShipmentBagEntity testShipmentBagEntity = ShipmentBagEntityBuilder.getValidShipmentBagEntity();
        when(shipmentBagQueryRepository.findByBarcode(testShipmentBagEntity.getBarcode())).thenReturn(testShipmentBagEntity);

        ShipmentBagEntity shipmentBagEntity = shipmentBagQueryService.getByBarcode(testShipmentBagEntity.getBarcode());
        assertThat(testShipmentBagEntity).isEqualTo(shipmentBagEntity);
    }

    @Test
    void happyPath_getAll() {
        List<ShipmentBagEntity> testShipmentBagEntities = ShipmentBagEntityBuilder.getValidShipmentBagEntities();
        when(shipmentBagQueryRepository.findAll()).thenReturn(testShipmentBagEntities);

        List<ShipmentBagEntity> shipmentBagEntities = shipmentBagQueryService.getAll();
        assertThat(testShipmentBagEntities).isEqualTo(shipmentBagEntities);
    }
}
