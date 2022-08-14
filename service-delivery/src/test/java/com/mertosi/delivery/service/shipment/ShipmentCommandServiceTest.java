package com.mertosi.delivery.service.shipment;

import com.mertosi.delivery.common.enums.ShipmentStatus;
import com.mertosi.delivery.common.exception.DeliveryException;
import com.mertosi.delivery.model.dto.request.ShipmentRequest;
import com.mertosi.delivery.model.dto.request.ShipmentRequestBuilder;
import com.mertosi.delivery.model.entity.DeliveryPointEntity;
import com.mertosi.delivery.model.entity.DeliveryPointEntityBuilder;
import com.mertosi.delivery.model.entity.ShipmentEntity;
import com.mertosi.delivery.model.entity.ShipmentEntityBuilder;
import com.mertosi.delivery.repository.shipment.ShipmentCommandRepository;
import com.mertosi.delivery.service.AbstractUnitTest;
import com.mertosi.delivery.service.deliverypoint.DeliveryPointQueryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ShipmentCommandServiceTest extends AbstractUnitTest {

    @InjectMocks
    ShipmentCommandServiceImpl shipmentCommandService;

    @Mock
    private DeliveryPointQueryService deliveryPointQueryService;
    @Mock
    private ShipmentCommandRepository shipmentCommandRepository;

    @Test
    void givenValidShipmentRequest_whenCreateShipmentEntity_thenReturnShipmentEntity() {
        List<ShipmentRequest> testShipmentRequests = ShipmentRequestBuilder.getValidShipmentRequests();
        ShipmentEntity testShipmentEntity = ShipmentEntityBuilder.getValidShipmentEntity();
        DeliveryPointEntity testDeliveryPointEntity = DeliveryPointEntityBuilder.getValidDeliveryPointEntity();

        when(shipmentCommandRepository.save(any(ShipmentEntity.class))).thenReturn(testShipmentEntity);
        when(deliveryPointQueryService.getByValue(any(Integer.class))).thenReturn(testDeliveryPointEntity);
        List<ShipmentEntity> shipmentEntities = shipmentCommandService.create(testShipmentRequests);

        assertThat(shipmentEntities).isNotEmpty();
    }

    @Test
    void givenValidStatus_whenUpdateShipmentEntity_thenReturnShipmentEntity() {
        ShipmentStatus status = ShipmentStatus.LOADED;
        ShipmentEntity testShipmentEntity = ShipmentEntityBuilder.getValidShipmentEntity();

        when(shipmentCommandRepository.save(any(ShipmentEntity.class))).thenReturn(testShipmentEntity);
        ShipmentEntity shipmentEntity = shipmentCommandService.updateStatus(testShipmentEntity, status);

        assertThat(testShipmentEntity).isEqualTo(shipmentEntity);
    }

    @Test
    void givenInvalidStatus_whenUpdateShipmentEntity_thenThrowDeliveryException() {
        ShipmentStatus status = ShipmentStatus.LOADED;
        ShipmentEntity testShipmentEntity = ShipmentEntityBuilder.getValidShipmentEntity();
        testShipmentEntity.setStatus(ShipmentStatus.UNLOADED);

        assertThrows(DeliveryException.class, () -> shipmentCommandService.updateStatus(testShipmentEntity, status));
    }
}
