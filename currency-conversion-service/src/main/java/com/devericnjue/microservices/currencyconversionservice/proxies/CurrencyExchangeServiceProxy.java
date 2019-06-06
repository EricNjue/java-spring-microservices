package com.devericnjue.microservices.currencyconversionservice.proxies;

import com.devericnjue.microservices.currencyconversionservice.dto.CurrencyConversionResult;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


//@FeignClient(name = "currency-exchange-service") // this will communicate with currency-exchange-service without using the zuul gateway
@FeignClient(name = "netflix-zuul-api-gateway-server")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

    @GetMapping("currency-exchange-service/currency-exchange/from/{from}/to/{to}")
    CurrencyConversionResult getExchangeRate(@PathVariable("from") String from, @PathVariable("to") String to);
}
