package com.mertosi.delivery.model.dto.request.delivery;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class DeliveryRequest {
    @NotBlank
    private String barcode;

    @AssertTrue(message = "barcode is not valid for shipment or bag")
    private boolean isBarcodeValid() {
        if (!StringUtils.hasText(barcode)) return true;
        return (barcode.startsWith("C") && barcode.length() == 7) ||
                (barcode.startsWith("P") && barcode.length() == 11);
    }
}
