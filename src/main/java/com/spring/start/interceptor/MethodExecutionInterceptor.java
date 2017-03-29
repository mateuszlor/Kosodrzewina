package com.spring.start.interceptor;

import lombok.experimental.var;
import lombok.extern.log4j.Log4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Created by Mateusz on 27.03.2017.
 */
@var
@Log4j
@Aspect
@Component
@Configuration
@PropertySource("classpath:i18n/messages.properties")
public class MethodExecutionInterceptor {
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

            return result;
        }
    }
}
