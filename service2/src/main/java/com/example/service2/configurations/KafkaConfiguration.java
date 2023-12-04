package com.example.service2.configurations;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration(proxyBeanMethods = false)
public class KafkaConfiguration {

    @Autowired
    KafkaTemplate kafkaTemplate;

    @PostConstruct
    void setup() {
        this.kafkaTemplate.setObservationEnabled(true);
    }

}