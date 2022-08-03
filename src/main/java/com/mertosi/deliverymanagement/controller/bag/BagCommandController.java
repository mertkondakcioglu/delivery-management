package com.mertosi.deliverymanagement.controller.bag;

import com.mertosi.deliverymanagement.common.mapper.bag.BagResponseMapper;
import com.mertosi.deliverymanagement.model.dto.request.BagRequest;
import com.mertosi.deliverymanagement.model.dto.response.BagResponse;
import com.mertosi.deliverymanagement.model.dto.response.BaseResponse;
import com.mertosi.deliverymanagement.service.bag.BagCommandService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bag")
public class BagCommandController {
    private final BagCommandService bagCommandService;
    private final BagResponseMapper mapper = Mappers.getMapper(BagResponseMapper.class);

    @PostMapping
    public BaseResponse<BagResponse> create(@RequestBody @Valid BagRequest request) {
        return BaseResponse.<BagResponse>builder()
                .data(mapper.map(bagCommandService.create(request)))
                .success(true)
                .build();
    }
}
