package com.spring.start.interceptor;

import com.spring.start.helper.ExecutionDetails;
import lombok.Getter;
import lombok.experimental.var;
import lombok.extern.log4j.Log4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mateusz on 27.03.2017.
 */
@var
@Log4j
@Aspect
@Component
public class MethodExecutionInterceptor {

    @Getter
    private Map<String, ExecutionDetails> methodExecutions = new HashMap<>();

    @Around("execution(* com.spring.start..*.*(..))")
    public Object logTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        var isFailed = false;
        var methodFullName = String.format("%s.%s()", joinPoint.getSignature().getDeclaringType().getCanonicalName(), joinPoint.getSignature().getName());

        var sw = new StopWatch();
        sw.start();

        log.info(String.format("### Method %s invoked", methodFullName));

        try {
            result = joinPoint.proceed();
        }
        catch (Exception e){
            isFailed = true;
            throw e;
        }
        finally {
            sw.stop();

            log.info(String.format("### Execution of method %s finished %s, took %s ms",
                    methodFullName,
                    isFailed ? "unsuccessfully" : "successfully",
                    sw.getTotalTimeMillis()));

            if(methodExecutions.containsKey(methodFullName)){
                methodExecutions.get(methodFullName).addExecution(sw.getTotalTimeMillis());
            }
            else {
                var details = new ExecutionDetails();
                details.addExecution(sw.getTotalTimeMillis());
                methodExecutions.put(methodFullName, details);
            }

            return result;
        }
    }
}
