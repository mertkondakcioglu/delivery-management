package com.mertosi.deliverymanagement.controller.deliverypoint;

import com.mertosi.deliverymanagement.common.mapper.deliverypoint.DeliveryPointResponseMapper;
import com.mertosi.deliverymanagement.model.dto.response.BaseResponse;
import com.mertosi.deliverymanagement.model.dto.response.DeliveryPointResponse;
import com.mertosi.deliverymanagement.service.deliverypoint.DeliveryPointQueryService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/delivery-point")
@Validated
public class DeliveryPointQueryController {
    private final DeliveryPointQueryService deliveryPointQueryService;
    private final DeliveryPointResponseMapper mapper = Mappers.getMapper(DeliveryPointResponseMapper.class);

    @GetMapping("/{value}")
    public BaseResponse<DeliveryPointResponse> getByValue(@PathVariable @NotNull Integer value) {
        return BaseResponse.<DeliveryPointResponse>builder()
                .data(mapper.map(deliveryPointQueryService.getByValue(value)))
                .success(true)
                .build();
    }
}
