package com.mertosi.deliverymanagement.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_delivery_error")
@Builder
public class DeliveryErrorEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -7581302024257538958L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "barcode", nullable = false)
    private String barcode;

    @Column(name = "delivery_point", nullable = false)
    private Integer deliveryPoint;

    @Column(name = "created_at")
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
