package com.example.task.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.example.task.service..*(..)) || execution(* com.example.task.controller..*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = signature.getDeclaringType().getSimpleName();
        String methodName = signature.getName();
        Object[] args = joinPoint.getArgs();

        logger.info("Entering {}.{} with args={}", className, methodName, Arrays.toString(args));

        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - start;
            logger.info("Exiting {}.{}; returned={} ({} ms)", className, methodName, result, duration);
            return result;
        } catch (Throwable t) {
            long duration = System.currentTimeMillis() - start;
            logger.error("Exception in {}.{} after {} ms: {}", className, methodName, duration, t.toString());
            throw t;
        }
    }
}
