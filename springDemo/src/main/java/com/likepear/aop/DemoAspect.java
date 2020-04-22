package com.likepear.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DemoAspect {

    @Pointcut("execution( * com.likepear..*(..))")
    public void pointcut(){

    }

    @AfterReturning(value="pointcut()",returning = "result")
    public void afterReturing(JoinPoint point,Object result){
        Object target = point.getTarget();
        String name = target.getClass().getName();
        System.out.println("代理了类："+name);

    }
}
