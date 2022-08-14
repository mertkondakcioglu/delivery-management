package com.mertosi.delivery.model.entity;

import com.mertosi.delivery.model.TestDataBuilder;

public class DeliveryErrorEntityBuilder extends TestDataBuilder<DeliveryErrorEntity> {

    public DeliveryErrorEntityBuilder() {
        super(DeliveryErrorEntity.class);
    }

    public DeliveryErrorEntityBuilder(boolean excludeRelations) {
        super(DeliveryErrorEntity.class, excludeRelations);
    }

    public static DeliveryErrorEntityBuilder getValidDeliveryErrorEntityWithoutRelations() {
        return new DeliveryErrorEntityBuilder(true);
    }

    public static DeliveryErrorEntity getValidDeliveryErrorEntity() {
        return new DeliveryErrorEntityBuilder().build();
    }
}
