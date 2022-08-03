package com.mertosi.deliverymanagement.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PackageStatus {
    CREATED(1), LOADED_INTO_BAG(2), LOADED(3), UNLOADED(4);

    private final Integer value;
}
