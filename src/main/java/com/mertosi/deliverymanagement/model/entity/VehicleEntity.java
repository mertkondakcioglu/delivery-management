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
@Table(name = "tbl_vehicle")
@Builder
@EqualsAndHashCode(callSuper = true)
public class VehicleEntity extends AbstractEntity {

    @Serial
    private static final long serialVersionUID = -3252345455177095611L;

    @Column(name = "license_plate", nullable = false)
    private String licensePlate;
}
