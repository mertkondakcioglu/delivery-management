package com.mertosi.delivery.service.delivery;

import com.mertosi.delivery.common.enums.DeliveryType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DeliveryProvider {
    private final Collection<Delivery> deliveries;
    private final Map<DeliveryType, Delivery> deliveryMap;

    @PostConstruct
    public void postConstruct() {
        deliveries.forEach(translator -> deliveryMap.put(translator.type(), translator));
    }

    public Delivery getDelivery(DeliveryType type) {
        return deliveryMap.get(type);
    }
}
