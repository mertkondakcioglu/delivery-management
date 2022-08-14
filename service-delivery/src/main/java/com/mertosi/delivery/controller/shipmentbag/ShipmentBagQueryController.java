package com.mertosi.delivery.controller.shipmentbag;

import com.mertosi.delivery.common.mapper.shipmentbag.ShipmentBagResponseMapper;
import com.mertosi.delivery.model.dto.response.BaseResponse;
import com.mertosi.delivery.model.dto.response.ShipmentBagResponse;
import com.mertosi.delivery.service.shipmentbag.ShipmentBagQueryService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shipment-bag")
public class ShipmentBagQueryController {
    private final ShipmentBagQueryService shipmentBagQueryService;
    private final ShipmentBagResponseMapper mapper = Mappers.getMapper(ShipmentBagResponseMapper.class);

    @GetMapping
    public BaseResponse<List<ShipmentBagResponse>> getAll() {
        return BaseResponse.<List<ShipmentBagResponse>>builder()
                .data(mapper.map(shipmentBagQueryService.getAll()))
                .build();
    }
}
