package com.example.service1.integration.service2;


import com.example.service1.integration.service2.response.BitCoinResponse;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Slf4j
@Service
public class Service2Service {

    /**
     *
     */
    final RestTemplate restTemplate;

    @Value("${service2.url}")
    String service2Url;

    private final ObservationRegistry observationRegistry;

    public Service2Service(RestTemplate restTemplate,  ObservationRegistry observationRegistry) {
        this.restTemplate = restTemplate;
        this.observationRegistry = observationRegistry;
    }

    public BitCoinResponse[] getBitCoinPrice() {
        return Observation.start("service2-sample", observationRegistry).observe(() -> {
            log.info("Apelam service2 pentru a lua lista de bitcoin");
            BitCoinResponse[] forObject = restTemplate.getForObject(service2Url.concat("/bitcoin"), BitCoinResponse[].class);
            log.debug("Am primit raspunsul de la service2: {}", (Object[]) forObject);
            log.debug("Lista contine {} elemente", Objects.requireNonNull(forObject).length);
            return forObject;
        });

    }

}
