package com.mertosi.delivery.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ShipmentStatus {
    CREATED(1), LOADED_INTO_BAG(2), LOADED(3), UNLOADED(4);

    private final Integer value;
}
