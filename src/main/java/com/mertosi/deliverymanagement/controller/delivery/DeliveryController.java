package com.mertosi.deliverymanagement.controller.delivery;

import com.mertosi.deliverymanagement.model.dto.request.delivery.MakeDeliveryRequest;
import com.mertosi.deliverymanagement.model.dto.response.BaseResponse;
import com.mertosi.deliverymanagement.model.dto.response.delivery.MakeDeliveryResponse;
import com.mertosi.deliverymanagement.service.delivery.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/delivery")
public class DeliveryController {
    private final DeliveryService deliveryService;

    @PostMapping
    public BaseResponse<MakeDeliveryResponse> makeDelivery(@RequestBody @Valid MakeDeliveryRequest request) {
        return BaseResponse.<MakeDeliveryResponse>builder()
                .data(deliveryService.makeDelivery(request))
                .success(true)
                .build();
    }
}
