package com.mertosi.deliverymanagement.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_shipment_bag")
@Builder
@EqualsAndHashCode(callSuper = true)
public class ShipmentBagEntity extends AbstractEntity {

    @Serial
    private static final long serialVersionUID = 1059604997612615065L;

    @OneToOne
    @JoinColumn(name = "barcode", referencedColumnName = "barcode")
    private ShipmentEntity barcode;

    @ManyToOne
    @JoinColumn(name = "bag_barcode", referencedColumnName = "barcode")
    private BagEntity bagBarcode;
}
