package com.mertosi.vehicle.common.exception;

import lombok.Getter;

import java.io.Serial;

@Getter
public class NotFoundException extends VehicleException {

    @Serial
    private static final long serialVersionUID = 5650686352273159136L;

    public NotFoundException(String value) {
        super(String.format("%s is not exists", value));
    }
}
