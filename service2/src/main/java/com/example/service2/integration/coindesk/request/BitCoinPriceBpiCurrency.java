package com.example.service2.integration.coindesk.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitCoinPriceBpiCurrency {
    private String code;
    private String rate;
    private String description;
    private Long rate_float;
}
