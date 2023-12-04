package com.example.service2.model.services;

import com.example.service2.model.beans.Bitcoin;
import com.example.service2.model.repositories.BitcoinRepository;
import io.micrometer.observation.annotation.Observed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BitcoinService {

    private BitcoinRepository bitcoinRepository;

    public List<Bitcoin> getBitCoinPrice(Date date) {
        log.debug("Scoatem lista de bitcoin din baza de date");
        List<Bitcoin> bitcoinList = bitcoinRepository.findByCreatedAtGreaterThan(date);
        log.debug("Lista de bitcoin din baza de date are {} elemente", bitcoinList.size());
        return bitcoinList;
    }

    @Observed(name = "bitcoin-save-in-db",
            contextualName = "bitcoin-save-in-db-span",
            lowCardinalityKeyValues = {"gabi", "gabi1"}
    )
    public Bitcoin save(Bitcoin bitcoin) {
        return bitcoinRepository.save(bitcoin);
    }

}
