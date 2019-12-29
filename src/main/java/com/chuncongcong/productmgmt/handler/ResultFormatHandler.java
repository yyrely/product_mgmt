package com.chuncongcong.productmgmt.handler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Sion on 2017/9/2.
 */
@Aspect
@Slf4j
@Component
public class ResultFormatHandler {

    @Pointcut("execution(* com.chuncongcong.productmgmt.controller.*.*(..))")
    public void pointcut() {
    }


    @Around("pointcut()")
    private Object resultFormat(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        Object object = joinPoint.proceed();
        if (!(object instanceof ResponseEntity)) {
            result = new ResultBean<>(object);
        } else {
            result = object;
        }
        return result;
    }


}
