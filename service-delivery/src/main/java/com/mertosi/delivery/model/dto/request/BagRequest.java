package com.mertosi.delivery.model.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.validation.constraints.*;

@Getter
@Setter
public class BagRequest {
    @NotBlank
    @Size(min = 7, max = 7)
    private String barcode;

    @NotNull
    @Positive
    private Integer deliveryPoint;

    @AssertTrue(message = "Bag barcode must start with C")
    private boolean isBagBarcodeValid() {
        if (!StringUtils.hasText(barcode)) return true;
        return barcode.startsWith("C");
    }
}
