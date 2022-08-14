package com.mertosi.delivery.model.dto.request.delivery;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@Setter
public class RouteRequest {
    @NotNull
    @Positive
    private Integer deliveryPoint;

    @NotNull
    @NotEmpty
    @Valid
    private List<DeliveryRequest> deliveries;
}
