package com.mertosi.vehicle.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serial;

@Getter
@RequiredArgsConstructor
public class VehicleException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -1243960273977082744L;

    private final String message;
}
