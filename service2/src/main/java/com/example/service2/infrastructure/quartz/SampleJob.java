package com.example.service2.infrastructure.quartz;

import com.example.service2.controllers.mapper.BitCoinResponseMapper;
import com.example.service2.infrastructure.kafka.KafkaMessagePubliser;
import com.example.service2.integration.coindesk.CoinDeskService;
import com.example.service2.integration.coindesk.request.BitCoinPrice;
import com.example.service2.model.beans.Bitcoin;
import com.example.service2.model.services.BitcoinService;
import io.micrometer.observation.annotation.Observed;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;


@Slf4j
@Component
public class SampleJob extends QuartzJobBean {

    @Autowired
    private CoinDeskService coinDeskService;

    @Autowired
    private BitcoinService bitcoinService;

    @Autowired
    KafkaMessagePubliser kafkaMessagePubliser;

    @Override
    @Observed(name = "bitcoin-quartz-job",
            contextualName = "bitcoin-quartz-job-span",
            lowCardinalityKeyValues = {"gabi", "gabi1"}
    )
    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {
        log.debug("Se executa jobul quartz {} ", "bitcoin-quartz-job");
        log.debug("Se apeleaza serviciul de la coindesk");
        BitCoinPrice price = coinDeskService.getPrice();
        bitcoinService.save(new Bitcoin()
                             .setAmount( price.getBpi().getUSD().getRate_float().doubleValue())
                             .setCurrency(price.getBpi().getUSD().getCode())
                             .setCreatedAt( Date.from(Instant.parse( price.getTime().getUpdatedISO())))
                            );
        log.debug("Am salvat in baza de date prima valoare");
        bitcoinService.save(new Bitcoin()
                .setAmount( price.getBpi().getEUR().getRate_float().doubleValue())
                .setCurrency(price.getBpi().getEUR().getCode())
                .setCreatedAt( Date.from(Instant.parse( price.getTime().getUpdatedISO())))
        );
        log.debug("Am salvat in baza de date a doua valoare");
        Bitcoin bitcoinSaved = bitcoinService.save(new Bitcoin()
                .setAmount(price.getBpi().getGBP().getRate_float().doubleValue())
                .setCurrency(price.getBpi().getGBP().getCode())
                .setCreatedAt(Date.from(Instant.parse(price.getTime().getUpdatedISO())))
        );
        log.debug("Am salvat in baza de date a treia valoare");

        log.debug("Am terminat de salvat");

        log.debug("Trimitem mesaj pe Kafka pt valoarea GBP");
        kafkaMessagePubliser.publish( BitCoinResponseMapper.INSTANCE.mapToBitCoinResponse1(bitcoinSaved));
        log.debug("Am trimis mesaj Kafka");

    }
}
