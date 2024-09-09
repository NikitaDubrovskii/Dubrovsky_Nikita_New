package dev.dubrovsky.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("execution(* dev.dubrovsky.service.*..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Class: {} called: '{}' with arguments: {}",
                joinPoint.getTarget().getClass().getName(),
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "execution(* dev.dubrovsky.service.*..*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("Class: {} return: '{}' from method: {}",
                joinPoint.getTarget().getClass().getName(),
                result,
                joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "execution(* dev.dubrovsky.service.*..*(..))", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        log.error("Class: {} method: '{}' threw an exception: {}",
                joinPoint.getTarget().getClass().getName(),
                joinPoint.getSignature().getName(),
                ex.getMessage());
    }

}
