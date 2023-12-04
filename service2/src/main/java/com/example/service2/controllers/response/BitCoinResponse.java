package com.example.service2.controllers.response;


import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class BitCoinResponse {
    private String currency;
    private Double price;
    private String date;
}
