package com.mertosi.deliverymanagement.controller.deliverypoint;

import com.mertosi.deliverymanagement.common.mapper.deliverypoint.DeliveryPointResponseMapper;
import com.mertosi.deliverymanagement.model.dto.request.DeliveryPointRequest;
import com.mertosi.deliverymanagement.model.dto.response.BaseResponse;
import com.mertosi.deliverymanagement.model.dto.response.DeliveryPointResponse;
import com.mertosi.deliverymanagement.service.deliverypoint.DeliveryPointCommandService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/delivery-point")
public class DeliveryPointCommandController {
    private final DeliveryPointCommandService deliveryPointCommandService;
    private final DeliveryPointResponseMapper mapper = Mappers.getMapper(DeliveryPointResponseMapper.class);

    @PostMapping
    public BaseResponse<DeliveryPointResponse> create(@RequestBody @Valid DeliveryPointRequest request) {
        return BaseResponse.<DeliveryPointResponse>builder()
                .data(mapper.map(deliveryPointCommandService.create(request)))
                .success(true)
                .build();
    }
}
