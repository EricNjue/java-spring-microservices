package com.devericnjue.microservices.currencyconversionservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CurrencyConversionResult {
    private Long id;
    private String from;
    private String to;
    private BigDecimal conversionMultiple;
    private BigDecimal quantity;
    private BigDecimal totalCalculatedAmount;
    private Integer port;

    public CurrencyConversionResult() {
    }

    public CurrencyConversionResult(Long id, String from, String to, BigDecimal conversionMultiple,
                                    BigDecimal quantity, BigDecimal totalCalculatedAmount, Integer port) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
        this.quantity = quantity;
        this.totalCalculatedAmount = totalCalculatedAmount;
        this.port = port;
    }
}
