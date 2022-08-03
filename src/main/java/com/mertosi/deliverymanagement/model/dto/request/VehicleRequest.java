package com.mertosi.deliverymanagement.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class VehicleRequest {
    @NotBlank
    private String licensePlate;
}
