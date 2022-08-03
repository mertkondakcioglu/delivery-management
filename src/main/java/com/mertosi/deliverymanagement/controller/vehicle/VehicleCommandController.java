package com.mertosi.deliverymanagement.controller.vehicle;

import com.mertosi.deliverymanagement.common.mapper.vehicle.VehicleResponseMapper;
import com.mertosi.deliverymanagement.model.dto.request.VehicleRequest;
import com.mertosi.deliverymanagement.model.dto.response.BaseResponse;
import com.mertosi.deliverymanagement.model.dto.response.VehicleResponse;
import com.mertosi.deliverymanagement.service.vehicle.VehicleCommandService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/vehicle")
public class VehicleCommandController {
    private final VehicleCommandService vehicleCommandService;
    private final VehicleResponseMapper mapper = Mappers.getMapper(VehicleResponseMapper.class);

    @PostMapping
    public BaseResponse<VehicleResponse> create(@RequestBody @Valid VehicleRequest request) {
        return BaseResponse.<VehicleResponse>builder()
                .data(mapper.map(vehicleCommandService.create(request)))
                .success(true)
                .build();
    }
}
