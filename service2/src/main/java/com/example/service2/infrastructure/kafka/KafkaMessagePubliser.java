package com.example.service2.infrastructure.kafka;

import com.example.service2.controllers.response.BitCoinResponse;
import io.micrometer.observation.annotation.Observed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class KafkaMessagePubliser {

    @Value("${kafka.topic}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Observed(name = "kafka.message.publish",
              contextualName = "kafka.message.publish-span",
            lowCardinalityKeyValues = {"gabi", "gabi1"})

    public void publish(BitCoinResponse message){
        CompletableFuture<SendResult<String, Object>> resultCompletableFuture = kafkaTemplate.send(topic, message);
        resultCompletableFuture.whenComplete((result, throwable) -> {
            if(throwable != null){
                log.error("Eroare la trimiterea mesajului pe Kafka ", throwable);
            }else{
                log.debug("Mesajul a fost trimis cu succes pe Kafka");
            }
        });

    }
}
