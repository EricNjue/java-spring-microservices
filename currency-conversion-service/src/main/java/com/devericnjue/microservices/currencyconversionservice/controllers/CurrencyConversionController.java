package com.devericnjue.microservices.currencyconversionservice.controllers;

import com.devericnjue.microservices.currencyconversionservice.dto.CurrencyConversionResult;
import com.devericnjue.microservices.currencyconversionservice.proxies.CurrencyExchangeServiceProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("currency-converter")
@Slf4j
public class CurrencyConversionController {

    private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

    public CurrencyConversionController(CurrencyExchangeServiceProxy currencyExchangeServiceProxy) {
        this.currencyExchangeServiceProxy = currencyExchangeServiceProxy;
    }

    // Using Raw RestTemplate
    @GetMapping("/raw/from/{from}/to/{to}/quantity/{quantity}")
    public ResponseEntity<CurrencyConversionResult> convertCurrency(@PathVariable String from, @PathVariable String to,
                                                                    @PathVariable BigDecimal quantity) {

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversionResult> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversionResult.class, uriVariables);

        CurrencyConversionResult response = responseEntity.getBody();

        CurrencyConversionResult conversionResult = new CurrencyConversionResult(response.getId(), from, to,
                response.getConversionMultiple(), quantity, quantity.multiply(response.getConversionMultiple()),
                response.getPort());

        return ResponseEntity.ok(conversionResult);
    }

    // Using Feign ....
    @GetMapping("/feign/from/{from}/to/{to}/quantity/{quantity}")
    public ResponseEntity<CurrencyConversionResult> convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
                                                                         @PathVariable BigDecimal quantity) {
        CurrencyConversionResult response = currencyExchangeServiceProxy.getExchangeRate(from, to);

        CurrencyConversionResult conversionResult = new CurrencyConversionResult(response.getId(), from, to,
                response.getConversionMultiple(), quantity, quantity.multiply(response.getConversionMultiple()),
                response.getPort());
        log.info("{}", response);
        return ResponseEntity.ok(conversionResult);
    }
}
