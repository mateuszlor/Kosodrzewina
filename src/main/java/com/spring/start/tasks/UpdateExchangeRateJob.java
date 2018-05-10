package com.spring.start.tasks;

import com.spring.start.rest.ExchangeRateService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j
@Component
public class UpdateExchangeRateJob {

	@Autowired
	private ExchangeRateService exchangeRateService;

	@Async
	@Scheduled(cron = "${jobs.updateExchangeRate.cron}")
	public void updateExchangeRate() {
		log.info("JOB: Start to execute " + this.getClass().getSimpleName());

		exchangeRateService.updateExchangeRates();

		log.info("JOB: Succesfully end " + this.getClass().getSimpleName());
	}

}
