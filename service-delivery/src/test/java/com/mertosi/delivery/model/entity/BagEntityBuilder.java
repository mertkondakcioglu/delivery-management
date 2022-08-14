package com.mertosi.delivery.model.entity;

import com.mertosi.delivery.model.TestDataBuilder;

import java.util.ArrayList;
import java.util.List;

public class BagEntityBuilder extends TestDataBuilder<BagEntity> {

    public BagEntityBuilder() {
        super(BagEntity.class);
    }

    public BagEntityBuilder(boolean excludeRelations) {
        super(BagEntity.class, excludeRelations);
    }

    public static BagEntityBuilder getValidBagEntityWithoutRelations() {
        return new BagEntityBuilder(true);
    }

    public static BagEntity getValidBagEntity() {
        return new BagEntityBuilder().withBarcode("C725799").build();
    }

    public static List<BagEntity> getValidBagEntities() {
        List<BagEntity> bagEntities = new ArrayList<>();
        bagEntities.add(getValidBagEntity());
        bagEntities.add(new BagEntityBuilder().withBarcode("C725800").build());
        return bagEntities;
    }

    public BagEntityBuilder withBarcode(String barcode) {
        data.setBarcode(barcode);
        return this;
    }
}
