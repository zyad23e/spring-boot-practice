package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

// java class that has collection of related advices
@Aspect
@Component
public class MyDemoLoggingAspect {

    // this is where we add all of our related advices for logging

    // let's start with a @Before advice
    // pointcut expression, run this code BEFORE - target object method: "public void addAccount()"
    @Before("execution(public void add*())")
    public void beforeAddAccountAdvice(){

        System.out.println("\n=====>>> Executing @Before advice on method");
    }
}
