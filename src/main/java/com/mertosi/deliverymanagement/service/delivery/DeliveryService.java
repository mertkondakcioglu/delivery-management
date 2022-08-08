package com.mertosi.deliverymanagement.service.delivery;

import com.mertosi.deliverymanagement.model.dto.request.delivery.MakeDeliveryRequest;
import com.mertosi.deliverymanagement.model.dto.response.delivery.MakeDeliveryResponse;

public interface DeliveryService {
    MakeDeliveryResponse makeDelivery(MakeDeliveryRequest request);

    boolean isDeliveryShipment(String barcode);

    boolean isDeliveryBag(String barcode);
}
