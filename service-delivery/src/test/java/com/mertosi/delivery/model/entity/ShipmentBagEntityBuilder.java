package com.mertosi.delivery.model.entity;

import com.mertosi.delivery.model.TestDataBuilder;

import java.util.ArrayList;
import java.util.List;

public class ShipmentBagEntityBuilder extends TestDataBuilder<ShipmentBagEntity> {

    public ShipmentBagEntityBuilder() {
        super(ShipmentBagEntity.class);
    }

    public ShipmentBagEntityBuilder(boolean excludeRelations) {
        super(ShipmentBagEntity.class, excludeRelations);
    }

    public static ShipmentBagEntityBuilder getValidShipmentBagEntityWithoutRelations() {
        return new ShipmentBagEntityBuilder(true);
    }

    public static ShipmentBagEntity getValidShipmentBagEntity() {
        return new ShipmentBagEntityBuilder()
                .withBarcode(ShipmentEntityBuilder.getValidShipmentEntity())
                .withBagBarcode(BagEntityBuilder.getValidBagEntity())
                .build();
    }

    public static List<ShipmentBagEntity> getValidShipmentBagEntities() {
        List<ShipmentBagEntity> shipmentBagEntities = new ArrayList<>();

        ShipmentBagEntity shipmentBagEntity = new ShipmentBagEntityBuilder()
                .withBarcode(new ShipmentEntityBuilder().withBarcode("P7988000122").build())
                .withBagBarcode(BagEntityBuilder.getValidBagEntity())
                .build();

        shipmentBagEntities.add(getValidShipmentBagEntity());
        shipmentBagEntities.add(shipmentBagEntity);
        return shipmentBagEntities;
    }

    public ShipmentBagEntityBuilder withBarcode(ShipmentEntity barcode) {
        data.setBarcode(barcode);
        return this;
    }

    public ShipmentBagEntityBuilder withBagBarcode(BagEntity bagBarcode) {
        data.setBagBarcode(bagBarcode);
        return this;
    }
}
