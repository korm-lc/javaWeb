package com.example.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Slf4j
@Aspect
@Component
public class RecordTimeAspect {

    @Pointcut("execution(* com.example.service.*.*(..))")
    public void pt(){

    }
    @Around("pt()")
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable {
        //1.记录方法运行开始时间
        long begin = System.currentTimeMillis();
        //2.执行原始的方法
        Object result = pjp.proceed();
        //3.记录方法运行的结束时间
        long end = System.currentTimeMillis();
        log.info("{}方法执行耗时：{}ms",pjp.getSignature(),end-begin);

        return result;
    }

    @Before("execution(* com.example.service.*.*(..))")
    public void before(JoinPoint joinPoint){
        log.info("MyAspect->before..");

        //1.获取目标类对象
        Object target = joinPoint.getTarget();
        log.info("获取目标对象：{}",target);
        //2.获取目标类
        String targetClass = joinPoint.getTarget().getClass().getName();
        log.info("获取目标类：{}",targetClass);
        //3.获取目标方法
        String targetMethod = joinPoint.getSignature().getName();
        log.info("获取目标方法：{}",targetMethod);
        //4.获取目标参数
        Object[] args = joinPoint.getArgs();
        log.info("获取目标方法参数：{}",args);
    }
}
