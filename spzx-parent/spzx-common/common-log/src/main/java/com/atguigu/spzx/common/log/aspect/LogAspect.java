package com.atguigu.spzx.common.log.aspect;

import com.atguigu.spzx.common.log.annotation.Log;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 作者:hfj
 * 功能:环绕通知切面类定义
 * 日期: 2025/12/29 10:43
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    //环绕通知
    @Around(value = "@annotation(sysLog)")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint , Log sysLog) {
        String title = sysLog.title();
        log.info("LogAspect...doAroundAdvice方法执行了"+title);
        System.out.println("LogAspect...doAroundAdvice方法执行了"+title);
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();// 执行业务方法
        } catch (Throwable e) {// 代码执行进入到catch中，业务方法执行产生异常
            throw new RuntimeException(e);
        }
        return proceed ;                                // 返回执行结果
    }
}
