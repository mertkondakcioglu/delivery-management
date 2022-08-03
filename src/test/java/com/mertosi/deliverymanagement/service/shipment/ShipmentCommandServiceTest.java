package com.mertosi.deliverymanagement.service.shipment;

import com.mertosi.deliverymanagement.common.enums.PackageStatus;
import com.mertosi.deliverymanagement.common.exception.DeliveryManagementException;
import com.mertosi.deliverymanagement.model.dto.request.ShipmentRequest;
import com.mertosi.deliverymanagement.model.dto.request.ShipmentRequestBuilder;
import com.mertosi.deliverymanagement.model.entity.DeliveryPointEntity;
import com.mertosi.deliverymanagement.model.entity.DeliveryPointEntityBuilder;
import com.mertosi.deliverymanagement.model.entity.ShipmentEntity;
import com.mertosi.deliverymanagement.model.entity.ShipmentEntityBuilder;
import com.mertosi.deliverymanagement.repository.shipment.ShipmentCommandRepository;
import com.mertosi.deliverymanagement.service.AbstractUnitTest;
import com.mertosi.deliverymanagement.service.deliverypoint.DeliveryPointQueryService;
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
        PackageStatus status = PackageStatus.LOADED;
        ShipmentEntity testShipmentEntity = ShipmentEntityBuilder.getValidShipmentEntity();

        when(shipmentCommandRepository.save(any(ShipmentEntity.class))).thenReturn(testShipmentEntity);
        ShipmentEntity shipmentEntity = shipmentCommandService.updateStatus(testShipmentEntity, status);

        assertThat(testShipmentEntity).isEqualTo(shipmentEntity);
    }

    @Test
    void givenInvalidStatus_whenUpdateShipmentEntity_thenThrowDeliveryManagementException() {
        PackageStatus status = PackageStatus.LOADED;
        ShipmentEntity testShipmentEntity = ShipmentEntityBuilder.getValidShipmentEntity();
        testShipmentEntity.setStatus(PackageStatus.UNLOADED);

        assertThrows(DeliveryManagementException.class, () -> shipmentCommandService.updateStatus(testShipmentEntity, status));
    }
}
