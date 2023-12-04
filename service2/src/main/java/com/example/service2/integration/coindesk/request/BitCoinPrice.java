package com.example.service2.integration.coindesk.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitCoinPrice {

    private BitCoinPriceTime time;
    private String disclaimer;
    private BitCoinPriceBpi bpi;

}
