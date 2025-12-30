package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


// java class that has collection of related advices
@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    // this is where we add all of our related advices for logging

    // let's start with a @Before advice
    // pointcut expression, run this code BEFORE - target object method:

    // JoinPoint has metadata about the method call
    @Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint){
        System.out.println("\n=====>>> Executing @Before advice on method");

        // display the method signature
        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();
        System.out.println("Method: " + methodSignature);

        // display method arguments
        // get args
        Object[] args = theJoinPoint.getArgs();

        // loop through args
        for (Object o: args){
            System.out.println(o);

            if (o instanceof Account){
                // downcast and print Account specific stuff
                Account theAccount = (Account) o;
                System.out.println("Account name: " + theAccount.getName());
                System.out.println("Account level: " + theAccount.getLevel());
            }
        }
    }
}
