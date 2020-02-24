package testAOP.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    // Pointcut declaration , reuse pointcut expressions
    @Pointcut("execution(* testAOP.AccountDao.addAccount(..))")
    private void forDaoPackage() {

    }

    @Pointcut("execution(* testAOP.AccountDao.*(..))")
    private void forAccount() {

    }

    @Before("forDaoPackage()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        System.out.println("\n=======> Executing @Before advice on addAccount()");

        // display the method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println(methodSignature);

        // display method arguments
        Object[] args = joinPoint.getArgs();
        for(Object o : args) {
            System.out.println(o);
        }

    }

    @Before("forAccount()")
    public void beforeAccountActivity() {
        System.out.println("\n=======> Executing @Before account activity");
    }
}
