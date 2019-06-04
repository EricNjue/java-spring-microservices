package com.devericnjue.microservices.currencyexchangeservice.models;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "currency_exchanges")
@Data
public class ExchangeValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "currency_from")
    private String from;

    @Column(name = "currency_to")
    private String to;

    private Integer port;

    private Double conversionMultiple;

    public ExchangeValue() {
    }

    public ExchangeValue(String from, String to) {
        this.from = from;
        this.to = to;
    }
}
