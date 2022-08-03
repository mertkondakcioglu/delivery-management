package com.mertosi.deliverymanagement.model.dto.request.delivery;

import com.mertosi.deliverymanagement.common.enums.DeliveryPoint;
import com.mertosi.deliverymanagement.model.TestDataBuilder;

import java.util.ArrayList;
import java.util.List;

public class RouteRequestBuilder extends TestDataBuilder<RouteRequest> {

    public RouteRequestBuilder() {
        super(RouteRequest.class);
    }

    public static List<RouteRequest> getValidRouteRequests() {
        List<RouteRequest> routeRequests = new ArrayList<>();
        List<DeliveryRequest> deliveries = DeliveryRequestBuilder.getValidDeliveryRequests();
        List<DeliveryRequest> deliveryWithBag = List.of(new DeliveryRequestBuilder().withBarcode("C725800").build());

        routeRequests.add(new RouteRequestBuilder().withDeliveryRequests(deliveryWithBag).withDeliveryPoint(DeliveryPoint.BRANCH.getValue()).build());
        routeRequests.add(new RouteRequestBuilder().withDeliveryRequests(deliveries).withDeliveryPoint(DeliveryPoint.DISTRIBUTION_CENTER.getValue()).build());
        return routeRequests;
    }

    public RouteRequestBuilder withDeliveryRequests(List<DeliveryRequest> deliveries) {
        data.setDeliveries(deliveries);
        return this;
    }

    public RouteRequestBuilder withDeliveryPoint(Integer deliveryPoint) {
        data.setDeliveryPoint(deliveryPoint);
        return this;
    }
}
