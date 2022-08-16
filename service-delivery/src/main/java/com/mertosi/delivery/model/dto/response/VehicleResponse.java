package com.mertosi.delivery.model.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class VehicleResponse extends AbstractResponse {
    private String licensePlate;
}
