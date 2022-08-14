package com.mertosi.vehicle.repository;

import com.mertosi.vehicle.model.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleCommandRepository extends JpaRepository<VehicleEntity, Long> {
}
