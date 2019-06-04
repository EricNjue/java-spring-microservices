package com.devericnjue.microservices.currencyexchangeservice.controllers;

import com.devericnjue.microservices.currencyexchangeservice.models.ExchangeValue;
import com.devericnjue.microservices.currencyexchangeservice.repositories.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("currency-exchange")
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;
    private CurrencyExchangeRepository currencyExchangeRepository;

    public CurrencyExchangeController(CurrencyExchangeRepository currencyExchangeRepository) {
        this.currencyExchangeRepository = currencyExchangeRepository;
    }

    @GetMapping("/from/{from}/to/{to}")
    public ResponseEntity<ExchangeValue> getExchangeRate(@PathVariable String from, @PathVariable String to) {
        ExchangeValue exchangeValue = currencyExchangeRepository.findByFromAndTo(from, to);
        exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        return ResponseEntity.ok(exchangeValue);
    }
}
