package com.mertosi.delivery.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BagStatus {
    CREATED(1), LOADED(3), UNLOADED(4);

    private final Integer value;
}
