package com.example.service2.integration.coindesk.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitCoinPriceBpi {

    @JsonProperty("USD")
    private BitCoinPriceBpiCurrency USD;
    @JsonProperty("GBP")
    private BitCoinPriceBpiCurrency GBP;
    @JsonProperty("EUR")
    private BitCoinPriceBpiCurrency EUR;
}
