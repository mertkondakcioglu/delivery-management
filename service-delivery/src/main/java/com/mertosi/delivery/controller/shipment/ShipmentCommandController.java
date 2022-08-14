package com.mertosi.delivery.controller.shipment;

import com.mertosi.delivery.common.mapper.shipment.ShipmentResponseMapper;
import com.mertosi.delivery.model.dto.request.ShipmentRequest;
import com.mertosi.delivery.model.dto.response.BaseResponse;
import com.mertosi.delivery.model.dto.response.ShipmentResponse;
import com.mertosi.delivery.service.shipment.ShipmentCommandService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shipment")
@Validated
public class ShipmentCommandController {
    private final ShipmentCommandService shipmentCommandService;
    private final ShipmentResponseMapper mapper = Mappers.getMapper(ShipmentResponseMapper.class);

    @PostMapping
    public BaseResponse<List<ShipmentResponse>> create(@RequestBody @Valid @NotEmpty List<ShipmentRequest> requests) {
        return BaseResponse.<List<ShipmentResponse>>builder()
                .data(mapper.map(shipmentCommandService.create(requests)))
                .build();
    }
}
