package com.mertosi.delivery.feign;

import com.mertosi.delivery.model.dto.response.VehicleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("service-vehicle")
public interface VehicleClient {

    @RequestMapping("/api/v1/vehicle/{licensePlate}")
    VehicleResponse getByLicensePlate(@PathVariable("licensePlate") String licensePlate);
}
