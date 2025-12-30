package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// java class that has collection of related advices
@Aspect
@Component
public class MyDemoLoggingAspect {

    // this is where we add all of our related advices for logging

    // let's start with a @Before advice
    // pointcut expression, run this code BEFORE - target object method:

    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
    private void forDaoPackage(){}

    // create a pointcut for getter methods
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))")
    private void getter(){}

    // create a pointcut for setter methods
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))")
    private void setter(){}

    // create pointcut: include package ... exclude getter/setters
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    private void forDaoPackageNoGetterSetter(){}

    @Before("forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(){
        System.out.println("\n=====>>> Executing @Before advice on method");
    }

    @Before("forDaoPackageNoGetterSetter()")
    public void performApiAnalytics(){
        System.out.println("\n=====>>> Performing API analytics");
    }
}
