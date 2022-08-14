package com.mertosi.delivery.model.entity;

import com.mertosi.delivery.common.enums.BagStatus;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_bag")
@Builder
@EqualsAndHashCode(callSuper = true)
public class BagEntity extends AbstractEntity {

    @Serial
    private static final long serialVersionUID = -8253419732133602986L;

    @Column(name = "barcode", nullable = false)
    private String barcode;

    @ManyToOne
    @JoinColumn(name = "delivery_point", referencedColumnName = "value")
    private DeliveryPointEntity deliveryPoint;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Builder.Default
    private BagStatus status = BagStatus.CREATED;
}
