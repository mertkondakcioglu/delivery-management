package com.mertosi.deliverymanagement.controller.shipmentbag;

import com.mertosi.deliverymanagement.common.mapper.shipmentbag.ShipmentBagResponseMapper;
import com.mertosi.deliverymanagement.model.dto.request.ShipmentBagRequest;
import com.mertosi.deliverymanagement.model.dto.response.BaseResponse;
import com.mertosi.deliverymanagement.model.dto.response.ShipmentBagResponse;
import com.mertosi.deliverymanagement.service.shipmentbag.ShipmentBagCommandService;
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
@RequestMapping("/api/v1/shipment-bag")
@Validated
public class ShipmentBagCommandController {
    private final ShipmentBagCommandService shipmentBagCommandService;
    private final ShipmentBagResponseMapper mapper = Mappers.getMapper(ShipmentBagResponseMapper.class);

    @PostMapping
    public BaseResponse<List<ShipmentBagResponse>> create(@RequestBody @Valid @NotEmpty List<ShipmentBagRequest> requests) {
        return BaseResponse.<List<ShipmentBagResponse>>builder()
                .data(mapper.map(shipmentBagCommandService.create(requests)))
                .success(true)
                .build();
    }
}
