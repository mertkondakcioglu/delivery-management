package com.mertosi.deliverymanagement.model.dto.request.delivery;

import com.mertosi.deliverymanagement.model.TestDataBuilder;

import java.util.List;

public class MakeDeliveryRequestBuilder extends TestDataBuilder<MakeDeliveryRequest> {

    public MakeDeliveryRequestBuilder() {
        super(MakeDeliveryRequest.class);
    }

    public static MakeDeliveryRequest getValidMakeDeliveryRequest() {
        List<RouteRequest> route = RouteRequestBuilder.getValidRouteRequests();
        return new MakeDeliveryRequestBuilder().withRouteRequests(route).build();
    }

    public MakeDeliveryRequestBuilder withRouteRequests(List<RouteRequest> route) {
        data.setRoute(route);
        return this;
    }
}
