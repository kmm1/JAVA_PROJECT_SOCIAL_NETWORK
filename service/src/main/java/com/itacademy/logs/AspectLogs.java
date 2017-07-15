package com.itacademy.logs;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

@Aspect
public class AspectLogs {
    private final Logger LOGGER = Logger.getLogger(AspectLogs.class);


    @Pointcut("execution(* com.itacademy.service.*.*(..))")
    public void logingServiceMethods() {
    }

    @AfterReturning(pointcut = ("execution(* com.itacademy.service.*.*(..))"),
            returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        LOGGER.info("******");
        LOGGER.info("logAfterReturning() is running!");
        LOGGER.info("перехват : " + joinPoint.getSignature().getName());
        LOGGER.info("перехваченные аргументы : " + Arrays.toString(joinPoint.getArgs()));
        LOGGER.info("Method returned value is : " + result);
        LOGGER.info("******");
    }
}
