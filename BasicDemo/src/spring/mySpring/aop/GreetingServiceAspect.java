package spring.mySpring.aop;


import spring.core.annotations.After;
import spring.core.annotations.Aspect;
import spring.core.annotations.Before;
import spring.core.annotations.Component;

import java.lang.reflect.Method;


@Aspect
@Component
public class GreetingServiceAspect {


    @Before(value = "edu.ecnu.di.GreetingServiceImpl.greet")
    public void beforeAdvice(Method method, Object... args) {
        System.out.println("Before method:" + method);
    }


    @After(value = "edu.ecnu.di.GreetingServiceImpl.greet")
    public void afterAdvice(Method method, Object... args) {
        System.out.println("After method:" + method);
    }
}
