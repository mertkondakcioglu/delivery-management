package com.mertosi.deliverymanagement.service.shipment;

import com.mertosi.deliverymanagement.common.exception.NotFoundException;
import com.mertosi.deliverymanagement.model.entity.ShipmentEntity;
import com.mertosi.deliverymanagement.model.entity.ShipmentEntityBuilder;
import com.mertosi.deliverymanagement.repository.shipment.ShipmentQueryRepository;
import com.mertosi.deliverymanagement.service.AbstractUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class ShipmentQueryServiceTest extends AbstractUnitTest {

    @InjectMocks
    ShipmentQueryServiceImpl shipmentQueryService;

    @Mock
    private ShipmentQueryRepository shipmentQueryRepository;

    @Test
    void givenValidBarcode_whenGetShipmentEntity_thenReturnShipmentEntity() {
        ShipmentEntity testShipmentEntity = ShipmentEntityBuilder.getValidShipmentEntity();
        when(shipmentQueryRepository.findByBarcode(testShipmentEntity.getBarcode())).thenReturn(Optional.of(testShipmentEntity));

        ShipmentEntity shipmentEntity = shipmentQueryService.getByBarcode(testShipmentEntity.getBarcode());
        assertThat(testShipmentEntity).isEqualTo(shipmentEntity);
    }

    @Test
    void givenInvalidBarcode_whenGetShipmentEntity_thenThrowNotFoundException() {
        String barcode = "P1234567890";
        assertThrows(NotFoundException.class, () -> shipmentQueryService.getByBarcode(barcode));
    }

    @Test
    void happyPath_getAll() {
        List<ShipmentEntity> testShipmentEntities = ShipmentEntityBuilder.getValidShipmentEntities();
        when(shipmentQueryRepository.findAll()).thenReturn(testShipmentEntities);

        List<ShipmentEntity> shipmentEntities = shipmentQueryService.getAll();
        assertThat(testShipmentEntities).isEqualTo(shipmentEntities);
    }

    @Test
    void givenValidBagBarcode_whenGetShipmentEntities_thenReturnShipmentEntities() {
        String barcode = "C725799";
        List<ShipmentEntity> testShipmentEntities = ShipmentEntityBuilder.getValidShipmentEntities();
        when(shipmentQueryRepository.findShipmentsInBagByBagBarcode(barcode)).thenReturn(testShipmentEntities);

        List<ShipmentEntity> shipmentEntities = shipmentQueryService.getShipmentsInBagByBagBarcode(barcode);
        assertThat(testShipmentEntities).isEqualTo(shipmentEntities);
    }
}
