package com.mertosi.deliverymanagement.model.dto.request.delivery;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class MakeDeliveryRequest {
    @NotBlank
    private String plate;

    @NotNull
    @NotEmpty
    @Valid
    private List<RouteRequest> route;
}
