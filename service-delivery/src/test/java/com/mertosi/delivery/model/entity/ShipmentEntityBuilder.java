package com.mertosi.delivery.model.entity;

import com.mertosi.delivery.model.TestDataBuilder;

import java.util.ArrayList;
import java.util.List;

public class ShipmentEntityBuilder extends TestDataBuilder<ShipmentEntity> {

    public ShipmentEntityBuilder() {
        super(ShipmentEntity.class);
    }

    public ShipmentEntityBuilder(boolean excludeRelations) {
        super(ShipmentEntity.class, excludeRelations);
    }

    public static ShipmentEntityBuilder getValidShipmentEntityWithoutRelations() {
        return new ShipmentEntityBuilder(true);
    }

    public static ShipmentEntity getValidShipmentEntity() {
        return new ShipmentEntityBuilder().withBarcode("P7988000121").build();
    }

    public static List<ShipmentEntity> getValidShipmentEntities() {
        List<ShipmentEntity> shipmentEntities = new ArrayList<>();
        shipmentEntities.add(getValidShipmentEntity());
        shipmentEntities.add(new ShipmentEntityBuilder().withBarcode("P7988000122").build());
        return shipmentEntities;
    }

    public ShipmentEntityBuilder withBarcode(String barcode) {
        data.setBarcode(barcode);
        return this;
    }
}
