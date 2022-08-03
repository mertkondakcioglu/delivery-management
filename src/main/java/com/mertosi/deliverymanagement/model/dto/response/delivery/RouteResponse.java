package com.mertosi.deliverymanagement.model.dto.response.delivery;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RouteResponse {
    private Integer deliveryPoint;
    private List<DeliveryResponse> deliveries;
}
