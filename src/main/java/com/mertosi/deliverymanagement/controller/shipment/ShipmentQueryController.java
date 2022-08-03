package com.mertosi.deliverymanagement.controller.shipment;

import com.mertosi.deliverymanagement.common.mapper.shipment.ShipmentResponseMapper;
import com.mertosi.deliverymanagement.model.dto.response.BaseResponse;
import com.mertosi.deliverymanagement.model.dto.response.ShipmentResponse;
import com.mertosi.deliverymanagement.service.shipment.ShipmentQueryService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shipment")
@Validated
public class ShipmentQueryController {
    private final ShipmentQueryService shipmentQueryService;
    private final ShipmentResponseMapper mapper = Mappers.getMapper(ShipmentResponseMapper.class);

    @GetMapping("/{barcode}")
    public BaseResponse<ShipmentResponse> getByBarcode(@PathVariable @NotBlank String barcode) {
        return BaseResponse.<ShipmentResponse>builder()
                .data(mapper.map(shipmentQueryService.getByBarcode(barcode)))
                .success(true)
                .build();
    }

    @GetMapping
    public BaseResponse<List<ShipmentResponse>> getAll() {
        return BaseResponse.<List<ShipmentResponse>>builder()
                .data(mapper.map(shipmentQueryService.getAll()))
                .success(true)
                .build();
    }
}
