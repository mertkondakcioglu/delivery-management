package com.mertosi.deliverymanagement.model.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.validation.constraints.*;

@Getter
@Setter
public class ShipmentRequest {
    @NotBlank
    @Size(min = 11, max = 11)
    private String barcode;

    @NotNull
    @Positive
    private Integer deliveryPoint;

    @NotNull
    @Positive
    private Integer volumetricWeight;

    @AssertTrue(message = "Package barcode must start with P")
    private boolean isPackageBarcodeValid() {
        if (!StringUtils.hasText(barcode)) return true;
        return barcode.startsWith("P");
    }
}
