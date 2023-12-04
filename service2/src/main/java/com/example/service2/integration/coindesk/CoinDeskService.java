package com.example.service2.integration.coindesk;


import com.example.service2.integration.coindesk.request.BitCoinPrice;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.tracing.Tracer;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Service
@AllArgsConstructor
public class CoinDeskService {

    private RestTemplate restTemplate;
    private Tracer tracer;
    private ObservationRegistry observationRegistry;


    public BitCoinPrice getPrice() {

        return Observation.start("coindesk-sample", observationRegistry).observe(() -> {
            log.info("<ACCEPTANCE_TEST> <TRACE:{}> Hello from consumer", this.tracer.currentSpan().context().traceId());
            BitCoinPrice forObject = restTemplate.getForObject("https://api.coindesk.com/v1/bpi/currentprice.json", BitCoinPrice.class);
            log.debug("Am primit raspunsul de la coindesk: {}", forObject);
            return forObject;
        });

    }

}
