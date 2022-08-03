package com.mertosi.deliverymanagement.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serial;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_delivery_point")
@Builder
@EqualsAndHashCode(callSuper = true)
public class DeliveryPointEntity extends AbstractEntity {

    @Serial
    private static final long serialVersionUID = -3053041124169814069L;

    @Column(name = "delivery_point", nullable = false)
    private String deliveryPoint;

    @Column(name = "value", nullable = false)
    private Integer value;
}
