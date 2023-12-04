package com.example.service1.infrastructure.kafka;


import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.tracing.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MyKafkaListener {

    private final Tracer tracer;

    private final ObservationRegistry observationRegistry;

    MyKafkaListener(Tracer tracer, ObservationRegistry observationRegistry) {
        this.tracer = tracer;
        this.observationRegistry = observationRegistry;
    }

    @KafkaListener(topics = "${kafka.topic}")
    void onMessage(Message message) {

        log.info("Am primit mesaj din Kafka {}", message);

        Observation.createNotStarted("on-message", this.observationRegistry).observe(() -> {
            log.info("Got message <{}>", message);
            log.info("<ACCEPTANCE_TEST> <TRACE:{}> Hello from consumer", this.tracer.currentSpan().context().traceId());
        });
    }


}
