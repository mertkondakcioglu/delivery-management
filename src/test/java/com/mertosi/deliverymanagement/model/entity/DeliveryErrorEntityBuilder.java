package com.mertosi.deliverymanagement.model.entity;

import com.mertosi.deliverymanagement.model.TestDataBuilder;

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
