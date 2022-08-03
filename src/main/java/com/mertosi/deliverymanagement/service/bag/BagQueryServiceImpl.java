package com.mertosi.deliverymanagement.service.bag;

import com.mertosi.deliverymanagement.common.enums.BagStatus;
import com.mertosi.deliverymanagement.common.exception.NotFoundException;
import com.mertosi.deliverymanagement.model.entity.BagEntity;
import com.mertosi.deliverymanagement.repository.bag.BagQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BagQueryServiceImpl implements BagQueryService {

    private final BagQueryRepository bagQueryRepository;

    @Override
    public BagEntity getByBarcode(String barcode) {
        return bagQueryRepository.findByBarcode(barcode)
                .orElseThrow(() -> new NotFoundException(barcode));
    }

    @Override
    public List<BagEntity> getAllByStatus(BagStatus status) {
        return bagQueryRepository.findAllByStatus(status);
    }
}
