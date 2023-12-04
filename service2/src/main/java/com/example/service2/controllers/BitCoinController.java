package com.example.service2.controllers;


import com.example.service2.controllers.mapper.BitCoinResponseMapper;
import com.example.service2.controllers.response.BitCoinResponse;
import com.example.service2.model.services.BitcoinService;
import io.micrometer.observation.annotation.Observed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class BitCoinController {

    private BitcoinService bitcoinService;


    @Observed(name = "bitcoin-rest",
              contextualName = "bitcoin-rest-span",
              lowCardinalityKeyValues = {"gabi", "gabi1"}
    )
    @GetMapping("/bitcoin")
    public ResponseEntity<List<BitCoinResponse>> getBitCoinPrice() {

        log.info("Am ajuns in BitCoin Controller pe GET");

        return ResponseEntity.ok(
                BitCoinResponseMapper.INSTANCE.mapToBitCoinResponse(bitcoinService.getBitCoinPrice(
                        Date.from( Instant.now().minus(3, ChronoUnit.DAYS))))
        );
    }


}
