package com.example.service1.controller.response;


import com.example.service1.integration.service2.Service2Service;
import com.example.service1.integration.service2.response.BitCoinResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BitCoinController {

    @Autowired
    Service2Service service2Service;

    @GetMapping("/b1")
    public ResponseEntity<BitCoinResponse[]> getBitCoinPrice(String aaa) {

        log.debug("Am ajuns in BitCoin Controller pe GET in service1");
        return ResponseEntity.ok(service2Service.getBitCoinPrice());


    }

}
