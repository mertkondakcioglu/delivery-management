package com.mertosi.delivery.model.dto.request.delivery;

import com.mertosi.delivery.model.TestDataBuilder;

import java.util.ArrayList;
import java.util.List;

public class DeliveryRequestBuilder extends TestDataBuilder<DeliveryRequest> {

    public DeliveryRequestBuilder() {
        super(DeliveryRequest.class);
    }

    public static List<DeliveryRequest> getValidDeliveryRequests() {
        List<DeliveryRequest> deliveryRequests = new ArrayList<>();
        deliveryRequests.add(new DeliveryRequestBuilder().withBarcode("P7988000121").build());
        deliveryRequests.add(new DeliveryRequestBuilder().withBarcode("P7988000122").build());
        deliveryRequests.add(new DeliveryRequestBuilder().withBarcode("C725799").build());
        return deliveryRequests;
    }

    public DeliveryRequestBuilder withBarcode(String barcode) {
        data.setBarcode(barcode);
        return this;
    }
}
