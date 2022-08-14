package com.mertosi.delivery.service.bag;

import com.mertosi.delivery.common.enums.BagStatus;
import com.mertosi.delivery.common.exception.NotFoundException;
import com.mertosi.delivery.model.entity.BagEntity;
import com.mertosi.delivery.repository.bag.BagQueryRepository;
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
