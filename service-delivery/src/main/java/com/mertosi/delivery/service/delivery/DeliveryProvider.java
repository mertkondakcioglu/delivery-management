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
    private final Collection<BaseDelivery> deliveries;
    private final Map<DeliveryType, BaseDelivery> deliveryMap;

    @PostConstruct
    public void postConstruct() {
        deliveries.forEach(delivery -> deliveryMap.put(delivery.type(), delivery));
    }

    public BaseDelivery getDelivery(DeliveryType type) {
        return deliveryMap.get(type);
    }
}
