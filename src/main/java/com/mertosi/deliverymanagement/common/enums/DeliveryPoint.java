package com.mertosi.deliverymanagement.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DeliveryPoint {
    BRANCH(1), DISTRIBUTION_CENTER(2), TRANSFER_CENTER(3);

    private final Integer value;
}
