package com.spring.start.tasks;

import com.spring.start.interceptor.MethodExecutionInterceptor;
import lombok.experimental.var;
import lombok.extern.log4j.Log4j;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz on 29.03.2017.
 */
@var
@Log4j
@Component
public class ExecutionLoggerTask {

    private DateTime startDate = new DateTime();

    @Autowired
    private MethodExecutionInterceptor methodExecutionInterceptor;

    @Async
    @Scheduled(fixedRateString = "${jobs.executionLoggerTask.rate}")
    public void logExecutionInfo() {

        var sb = new StringBuilder();
        sb.append("Statistics:\n\r");

        var duration = new Interval(startDate, new DateTime()).toDuration();
        sb.append(String.format("\tApplication is running from %s - uptime: %s days %s hours %s seconds\n\r",
                startDate.toString("yyyy.MM.dd HH:mm:ss"),
                duration.getStandardDays(),
                duration.getStandardHours(),
                duration.getStandardSeconds()));

        sb.append("\tTop method execution time: (shortest | average | longest)\n\r");

        methodExecutionInterceptor.getMethodExecutions()
                .entrySet()
                .stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue().getLongestExecutionTime(), e1.getValue().getLongestExecutionTime()))
                .limit(10)
                .map(e->String.format("\t\t >>> %s - %s  ms | %s ms | %s ms\n\r",
                        e.getKey(),
                        e.getValue().getShortestExecutionTime(),
                        e.getValue().getAverageExecutionTime(),
                        e.getValue().getLongestExecutionTime()))
                .forEach(sb::append);

        sb.append("\tTop method execution count:\n\r");

        methodExecutionInterceptor.getMethodExecutions()
                .entrySet()
                .stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue().getExecutionsCount(), e1.getValue().getExecutionsCount()))
                .limit(10)
                .map(e->String.format("\t\t>>> %s - %s\n\r",
                        e.getKey(),
                        e.getValue().getExecutionsCount()))
                .forEach(sb::append);

        log.info(sb.toString());
    }

}
