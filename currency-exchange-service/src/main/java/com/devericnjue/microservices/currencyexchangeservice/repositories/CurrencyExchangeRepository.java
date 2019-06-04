package com.devericnjue.microservices.currencyexchangeservice.repositories;

import com.devericnjue.microservices.currencyexchangeservice.models.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<ExchangeValue,Long> {

    ExchangeValue findByFromAndTo(String from,String to);
}
