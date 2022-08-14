package com.mertosi.delivery.model.dto.request;

import com.mertosi.delivery.model.TestDataBuilder;

public class BagRequestBuilder extends TestDataBuilder<BagRequest> {

    public BagRequestBuilder() {
        super(BagRequest.class);
    }

    public static BagRequest getValidBagRequest() {
        return new BagRequestBuilder().withBarcode("C725799").build();
    }

    public BagRequestBuilder withBarcode(String barcode) {
        data.setBarcode(barcode);
        return this;
    }
}
