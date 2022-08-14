package com.mertosi.delivery.service.delivery;

import com.mertosi.delivery.model.dto.request.delivery.MakeDeliveryRequest;
import com.mertosi.delivery.model.dto.response.delivery.MakeDeliveryResponse;

public interface DeliveryService {
    MakeDeliveryResponse makeDelivery(MakeDeliveryRequest request);
}
