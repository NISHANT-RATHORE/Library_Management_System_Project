package org.example.library_management_system;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class CustomAspect {
    @Before("execution( * org.example.library_management_system.Controller.BookController.getBooks(..))")
    public void emitBeforeLogs(JoinPoint joinPoint){
        log.info("i am in the emit logs before : "+ joinPoint.getSignature());

    }

    @After("execution( * org.example.library_management_system.Controller.BookController.getBooks(..))")
    public void emitAfterLogs(JoinPoint joinPoint){
        log.info("i am in the emit logs after : "+ joinPoint.getSignature());

    }

    @Around("execution( * org.example.library_management_system.Service.BookService.getBooks(..))")
    public Object emitAfterLogs(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("i am in the around emit logs before : "+ proceedingJoinPoint.getSignature());
        Object response  = proceedingJoinPoint.proceed();
        log.info("i am in the around emit logs after : "+ proceedingJoinPoint.getSignature());
        return response;
    }
}
