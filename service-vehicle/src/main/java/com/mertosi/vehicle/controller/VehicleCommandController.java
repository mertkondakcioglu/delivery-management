package com.mertosi.vehicle.controller;

import com.mertosi.vehicle.common.mapper.VehicleResponseMapper;
import com.mertosi.vehicle.model.dto.request.VehicleRequest;
import com.mertosi.vehicle.model.dto.response.BaseResponse;
import com.mertosi.vehicle.model.dto.response.VehicleResponse;
import com.mertosi.vehicle.service.VehicleCommandService;
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
                .build();
    }
}
