package com.mertosi.delivery.controller.delivery;

import com.mertosi.delivery.model.dto.request.delivery.MakeDeliveryRequest;
import com.mertosi.delivery.model.dto.response.BaseResponse;
import com.mertosi.delivery.model.dto.response.delivery.MakeDeliveryResponse;
import com.mertosi.delivery.service.delivery.MakeDeliveryService;
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
    private final MakeDeliveryService makeDeliveryService;

    @PostMapping
    public BaseResponse<MakeDeliveryResponse> makeDelivery(@RequestBody @Valid MakeDeliveryRequest request) {
        return BaseResponse.<MakeDeliveryResponse>builder()
                .data(makeDeliveryService.makeDelivery(request))
                .build();
    }
}
