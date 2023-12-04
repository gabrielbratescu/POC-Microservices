package com.example.service1.integration.service2.response;


import lombok.Data;

@Data
public class BitCoinResponse {
    private String currency;
    private Double price;
    private String date;
}
