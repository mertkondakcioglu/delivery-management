package com.mertosi.deliverymanagement.repository.vehicle;

import com.mertosi.deliverymanagement.model.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleCommandRepository extends JpaRepository<VehicleEntity, Long> {
}
