package com.spring.start.entity;


import com.spring.start.rest.ExchangeRateJSON;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "exchange_rate")
@Getter @Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class ExchangeRate {

	@Id
	@Column(name = "code")
	String currencyCode;

	@Column(name = "name")
	String currencyName;

	@Column(name = "rate_table")
	String table;

	@Column(precision = 19, scale = 4)
	BigDecimal buy;

	@Column(precision = 19, scale = 4)
	BigDecimal sell;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date", columnDefinition="DATETIME")
	Date updateTime = new Date();

	public ExchangeRate(ExchangeRateJSON exchangeRateJSON) {
		currencyName = exchangeRateJSON.getCurrency();
		table = exchangeRateJSON.getTable();
		currencyCode = exchangeRateJSON.getCode();

		ExchangeRateJSON.Rates rates = exchangeRateJSON.getRates().get(0);
		buy = rates.getBid();
		sell = rates.getAsk();
	}

}
