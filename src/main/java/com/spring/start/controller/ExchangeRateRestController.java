package com.spring.start.controller;


import com.spring.start.annotations.RestAPIController;
import com.spring.start.entity.ExchangeRate;
import com.spring.start.rest.ExchangeRateService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RestAPIController
@Log
public class ExchangeRateRestController {

	private static final String GET_EXCHANGE_RATES = "getExchangeRates";

	@Autowired
	private ExchangeRateService exchangeRateService;

	@GetMapping(value = GET_EXCHANGE_RATES)
	public Iterable<ExchangeRate> getExchangeRatesByRest(Model model) {
		log.info("REST: Pobrano kursy walut");
		return exchangeRateService.getExchangeRates();
	}



}
