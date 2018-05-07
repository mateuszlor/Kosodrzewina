package com.spring.start.rest;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class ExchangeRateService {

    private static final String TODAY_EXCHANGE_DATE_URL = "http://api.nbp.pl/api/exchangerates/rates/c/{0}/today/?format=json";

    public String getTodaySingleRate(String currency) {
        String jsonResponse = "";
        try {
            String url = MessageFormat.format(TODAY_EXCHANGE_DATE_URL, currency);
            Client client = Client.create();
            WebResource resource = client.resource(url);
            ClientResponse response = resource.get(ClientResponse.class);
            String responseJson = response.getEntity(String.class);
            System.out.printf(responseJson);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonResponse;
    }

}
