package com.mertosi.deliverymanagement.repository.vehicle;

import com.mertosi.deliverymanagement.model.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleQueryRepository extends JpaRepository<VehicleEntity, Long> {
    Optional<VehicleEntity> findByLicensePlate(String name);
}
