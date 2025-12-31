package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;


// java class that has collection of related advices
@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    // this is where we add all of our related advices for logging

    // add a new advice for @Around on the findAccounts method
    // this advice will execute BEFORE and AFTER the findAccounts() method is ran
    @Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {

        // print out method we are advising on
        String method = theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @Around on method: " + method);

        // get begin timestamp
        long begin = System.currentTimeMillis();

        // now, let's execute the method
        Object result = theProceedingJoinPoint.proceed();

        // get end timestamp
        long end = System.currentTimeMillis();

        // compute duration and display it
        long duration = end - begin;
        System.out.println("\n=====> Duration: " + duration / 1000.0 + " seconds");

        return result;
    }

    // add a new advice for @After on the findAccounts method
    // this advice will execute AFTER the findAccounts() method is ran regardless if it was successfull or failure
    @After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint){

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @After (finally) on method: " + method);
    }

    // add a new advice for @AfterThrowing on the findAccounts method
    // this advice will execute AFTER the findAccounts() method is ran and an exception is thrown.
    @AfterThrowing(pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))", throwing = "theExc")
    public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc){

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterThrowing on method: " + method);

        // log the exception
        System.out.println("\n=====>>> The exception is: " + theExc);

    }

    // add a new advice for @AfterReturning on the findAccounts method
    // this advice will execute AFTER the findAccounts() method is ran and successfully executed with no errors.
    @AfterReturning(pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))", returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result){

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterReturning on method: " + method);

        // print out the results of the method call
        System.out.println("\n=====>>> result is: " + result);

        // let's post-process the data ... let's modify it

        // convert the account names to uppercase
        convertAccountNamesToUpperCase(result);

        System.out.println("\n=====>>> result is: " + result);

    }

    private void convertAccountNamesToUpperCase(List<Account> result) {

        // loop through accounts
        for (Account a: result){

            // get uppercase version of name
            String upperName = a.getName().toUpperCase();

            // update the name on the account
            a.setName(upperName);
        }
    }

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
