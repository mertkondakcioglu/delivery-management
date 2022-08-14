package com.mertosi.delivery.model.dto.response.delivery;

import com.mertosi.delivery.model.TestDataBuilder;

public class RouteResponseBuilder extends TestDataBuilder<RouteResponse> {

    public RouteResponseBuilder() {
        super(RouteResponse.class);
    }

    public static RouteResponse getValidRouteResponse() {
        return new RouteResponseBuilder().build();
    }
}
