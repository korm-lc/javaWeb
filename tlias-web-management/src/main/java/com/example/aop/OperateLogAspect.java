package com.example.aop;

import com.example.mapper.OperateLogMapper;
import com.example.pojo.OperateLog;
import com.example.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperateLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;


    //切面方法
    @Around("@annotation(com.example.anno.LogOperation)")
    public Object saveLog(ProceedingJoinPoint pjp) throws Throwable {
        Long startTime = System.currentTimeMillis();
        Object result = pjp.proceed();
        Long endTime = System.currentTimeMillis();
        Long costTime= endTime-startTime;



        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(getEmpId());
        operateLog.setOperateTime(LocalDateTime.now());
        operateLog.setCostTime(costTime);
        operateLog.setClassName(pjp.getTarget().getClass().getName());
        operateLog.setMethodName(pjp.getSignature().getName());
        operateLog.setMethodParams(Arrays.toString(pjp.getArgs()));
        operateLog.setReturnValue(result.toString());

        operateLogMapper.insert(operateLog);
        return result;
    }

    public Integer getEmpId(){
        return CurrentHolder.get();
    }
}
