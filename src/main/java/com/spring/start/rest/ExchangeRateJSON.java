package com.spring.start.rest;

import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter @Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class ExchangeRateJSON {

	String code;

	String currency;

	String table;

	List<Rates> rates = new ArrayList();

	@Getter @Setter
	@NoArgsConstructor @AllArgsConstructor
	public class Rates {

		String no;

		Date effectiveDate;

		BigDecimal bid;

		BigDecimal ask;

	}

}
