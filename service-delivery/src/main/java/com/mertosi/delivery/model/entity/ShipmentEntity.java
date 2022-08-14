package com.mertosi.delivery.model.entity;

import com.mertosi.delivery.common.enums.ShipmentStatus;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_shipment")
@Builder
@EqualsAndHashCode(callSuper = true)
public class ShipmentEntity extends AbstractEntity {

    @Serial
    private static final long serialVersionUID = 443389280243350194L;

    @Column(name = "barcode", nullable = false)
    private String barcode;

    @ManyToOne
    @JoinColumn(name = "delivery_point", referencedColumnName = "value")
    private DeliveryPointEntity deliveryPoint;

    @Column(name = "volumetric_weight", nullable = false)
    private Integer volumetricWeight;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Builder.Default
    private ShipmentStatus status = ShipmentStatus.CREATED;
}
