package com.spike.CRM.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {

    // setup logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    // setup pointcut declarations
    @Pointcut("execution(* com.spike.CRM.controller.*.*(..))")
    private void forControllerPackage() {

    }

    @Pointcut("execution(* com.spike.CRM.service.*.*(..))")
    private void forServicePackage() {

    }
    @Pointcut("execution(* com.spike.CRM.dao.*.*(..))")
    private void forDaoPackage() {

    }

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {

    }

    // add @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {

        // display method we are calling
        String method = joinPoint.getSignature().toShortString();
        myLogger.info("======>> in @Before: calling method: " + method);

        // display the args to the method

        // get args
        Object[] args = joinPoint.getArgs();

        // loop and display args
        for(Object arg: args) {
            myLogger.info("======>> argument: " + arg);
        }
    }

    // add @AfterReturning advice
    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "result"
    )
    public void afterReturning(JoinPoint joinPoint, Object result) {

        // display method we are returning from
        String method = joinPoint.getSignature().toShortString();
        myLogger.info("======>> in @AfterReturning: from method: " + method);

        // display data returned
        myLogger.info("======>> result: " + result);

    }
}
