package com.mertosi.deliverymanagement.controller.vehicle;

import com.mertosi.deliverymanagement.common.mapper.vehicle.VehicleResponseMapper;
import com.mertosi.deliverymanagement.model.dto.response.BaseResponse;
import com.mertosi.deliverymanagement.model.dto.response.VehicleResponse;
import com.mertosi.deliverymanagement.service.vehicle.VehicleQueryService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/vehicle")
@Validated
public class VehicleQueryController {
    private final VehicleQueryService vehicleQueryService;
    private final VehicleResponseMapper mapper = Mappers.getMapper(VehicleResponseMapper.class);

    @GetMapping("/{licensePlate}")
    public BaseResponse<VehicleResponse> getByLicensePlate(@PathVariable @NotBlank String licensePlate) {
        return BaseResponse.<VehicleResponse>builder()
                .data(mapper.map(vehicleQueryService.getByLicensePlate(licensePlate)))
                .success(true)
                .build();
    }
}
