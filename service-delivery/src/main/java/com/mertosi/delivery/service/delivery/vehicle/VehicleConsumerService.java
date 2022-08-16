package com.mertosi.delivery.service.delivery.vehicle;

import com.mertosi.delivery.feign.VehicleClient;
import com.mertosi.delivery.model.dto.response.VehicleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class VehicleConsumerService {
    private final VehicleClient vehicleClient;
    private final CircuitBreakerFactory<?, ?> circuitBreakerFactory;

    @KafkaListener(topics = "#{'${spring.kafka.consumer.topic.name}'}",
            containerFactory = "kafkaListenerContainerFactory")
    public void consume(String licensePlate) {
        VehicleResponse vehicleResponse = circuitBreakerFactory.create("getByLicensePlate").run(
                () -> vehicleClient.getByLicensePlate(licensePlate).getData(),
                t -> VehicleResponse.builder().licensePlate("34 TL 34").build());

        log.info(String.format("Vehicle received -> %s", vehicleResponse.getLicensePlate()));
    }
}
