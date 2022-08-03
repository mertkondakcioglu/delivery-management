package com.mertosi.deliverymanagement.model.entity;

import com.mertosi.deliverymanagement.common.enums.DeliveryPoint;
import com.mertosi.deliverymanagement.model.TestDataBuilder;

public class DeliveryPointEntityBuilder extends TestDataBuilder<DeliveryPointEntity> {

    public DeliveryPointEntityBuilder() {
        super(DeliveryPointEntity.class);
    }

    public DeliveryPointEntityBuilder(boolean excludeRelations) {
        super(DeliveryPointEntity.class, excludeRelations);
    }

    public static DeliveryPointEntityBuilder getValidDeliveryPointEntityWithoutRelations() {
        return new DeliveryPointEntityBuilder(true);
    }

    public static DeliveryPointEntity getValidDeliveryPointEntity() {
        return new DeliveryPointEntityBuilder().withValue(DeliveryPoint.DISTRIBUTION_CENTER.getValue()).build();
    }

    public DeliveryPointEntityBuilder withValue(Integer value) {
        data.setValue(value);
        return this;
    }
}
