package com.spring.start.helper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz on 29.03.2017.
 */
public class ExecutionDetails {
    private List<Long> executions = new ArrayList<>();

    public int getExecutionsCount() {
        return executions.size();
    }

    public void addExecution(long executionTime) {
        executions.add(executionTime);
    }

    public long getShortestExecutionTime(){
        return executions.stream().min(Long::compareTo).get();
    }

    public long getLongestExecutionTime(){
        return executions.stream().max(Long::compareTo).get();
    }

    public long getAverageExecutionTime() {
        return Math.round(executions.stream().mapToLong(Long::longValue).average().getAsDouble());
    }
}
