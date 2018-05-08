package com.spring.start.rest;


import com.google.gson.Gson;
import com.spring.start.entity.ExchangeRate;
import com.spring.start.repository.ExchangeRateRepository;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

@Service
@Log4j
public class ExchangeRateService {

    private static final String TODAY_EXCHANGE_DATE_URL = "http://api.nbp.pl/api/exchangerates/rates/c/{0}/today/?format=json";
    private static final List<String> CURRENCIES = Arrays.asList("USD", "EUR", "GBP");

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    public void updateExchangeRates() {
        for (String currencyCode : CURRENCIES) {
            String jsonRate = getTodaySingleRate(currencyCode);
            ExchangeRate rate = new ExchangeRate(convertExchangeRateJSONToObject(jsonRate));
            saveExchangeRate(rate);
        }
    }

    private String getTodaySingleRate(String currency) {
        String jsonResponse = "";
        try {
            String url = MessageFormat.format(TODAY_EXCHANGE_DATE_URL, currency);
            Client client = Client.create();
            WebResource resource = client.resource(url);
            ClientResponse response = resource.get(ClientResponse.class);
            String responseJson = response.getEntity(String.class);
            System.out.printf(responseJson);
        } catch (Exception e) {
            log.error(e);
        }

        return jsonResponse;
    }

    private ExchangeRateJSON convertExchangeRateJSONToObject(String json) {
        ExchangeRateJSON exchangeRate = new Gson().fromJson(json, ExchangeRateJSON.class);
        return exchangeRate;
    }

    public void saveExchangeRate(ExchangeRate rate) {
        exchangeRateRepository.save(rate);
    }

    public Iterable<ExchangeRate> getExchangeRates() {
        return exchangeRateRepository.findAll();
    }

}
