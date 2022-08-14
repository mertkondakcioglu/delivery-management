package com.mertosi.delivery.repository.shipment;

import com.mertosi.delivery.model.entity.ShipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ShipmentQueryRepository extends JpaRepository<ShipmentEntity, Long> {
    Optional<ShipmentEntity> findByBarcode(String barcode);

    @Query("select shipment from ShipmentBagEntity sb " +
            "join sb.barcode shipment " +
            "where sb.bagBarcode.barcode = :bagBarcode")
    List<ShipmentEntity> findShipmentsInBagByBagBarcode(@Param("bagBarcode") String bagBarcode);
}
