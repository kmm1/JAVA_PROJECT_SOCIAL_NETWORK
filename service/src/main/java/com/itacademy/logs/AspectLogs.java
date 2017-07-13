package com.itacademy.logs;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspectLogs {
    private final Logger LOGGER = Logger.getLogger(AspectLogs.class);


    @Pointcut("execution(* com.itacademy.service.*.*(..))")
    public void logingServiceMethods() {
    }

    @Before("logingServiceMethods()")
    public void logBefore() {
        LOGGER.info("Service method BEFORE");
    }


    @After("logingServiceMethods()")
    public void logAfter() {
        LOGGER.info("Service method AFTER");
    }
}
