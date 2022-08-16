package com.mertosi.delivery.service.delivery.vehicle;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class VehicleProducerService {

    @Value("${spring.kafka.consumer.topic.name}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String licensePlate) {
        this.kafkaTemplate.send(topicName, licensePlate);
        log.info(String.format("Vehicle fetched with licensePlate -> %s", licensePlate));
    }
}
