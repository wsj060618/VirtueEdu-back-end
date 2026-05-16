package org.perswsj.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.perswsj.utils.CurrentHolder;
import org.perswsj.mapper.OperateLogMapper;
import org.perswsj.model.OperateLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 操作日志切面
 * 监控所有增删改方法并记录操作日志
 */
@Slf4j
@Aspect
@Component
public class RecordRunTimeAspect {
    @Autowired
    private OperateLogMapper operateLogMapper;

    /**
     * 监控增删改方法
     */
    @Around("@annotation(org.perswsj.anno.Log)")
    public Object recordOperateLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 记录开始时间
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        // 计算耗时
        long costTime = System.currentTimeMillis() - startTime;
            
        // 记录操作日志
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(getCurrentUserId());
        operateLog.setOperateTime(LocalDateTime.now());
        operateLog.setClassName(joinPoint.getTarget().getClass().getName());
        operateLog.setMethodName(joinPoint.getSignature().getName());
        operateLog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        operateLog.setReturnValue(result != null ? result.toString() : "void");
        operateLog.setCostTime(costTime);

        operateLogMapper.insert(operateLog);
        log.info("操作日志已记录: {}", operateLog);

        return result;
    }

    /**
     * 从线程本地存储中获取当前登录用户ID
     */
    private Integer getCurrentUserId() {
        return CurrentHolder.getCurrentId();
    }
}