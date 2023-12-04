package com.example.service2;

import com.example.service2.controllers.response.BitCoinResponse;
import com.example.service2.infrastructure.kafka.KafkaMessagePubliser;
import com.example.service2.integration.coindesk.CoinDeskService;
import com.example.service2.integration.coindesk.request.BitCoinPrice;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@SpringBootTest(classes = TestService2Application.class)
@ActiveProfiles("dev")
class Service2ApplicationTests {

    @Autowired
    CoinDeskService coinDeskService;

    @Autowired
    KafkaMessagePubliser kafkaMessagePubliser;


    @Test
    void shouldGetCoinPrice() {

        BitCoinPrice price = coinDeskService.getPrice();
        System.out.println("AAAAAA "+price);

    }

    @Test
    void shouldSentMessageToKafka() {
        kafkaMessagePubliser.publish(new BitCoinResponse().setCurrency("EUR").setPrice(100.0).setDate("2020-01-01"));
    }

}
