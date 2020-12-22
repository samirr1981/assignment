package com.indices.loggers;

import java.time.LocalTime;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class LogDebug {
	ThreadLocal<Long> serviceTime = new ThreadLocal<>();
    private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Pointcut("execution(* com.indices.*.*.*(..))")
	public void pointcut() {
		//method left empty
	}
	
	@Before("pointcut()")
	public void doBefore(JoinPoint joinPoint) throws ClassNotFoundException{
		serviceTime.set(System.currentTimeMillis());
		log.debug("LogService.doBefore() with CLASS_METHOD:" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
		log.debug("SERVICE ARGS : " + Arrays.toString(joinPoint.getArgs()));
	}
	
	@AfterReturning(returning = "object", pointcut = "pointcut()")
	public void doAfterRequrning(Object object) {
		log.debug("LogService.doAfterReturning(),SERVICE_Response return object={}", object);
		log.debug("SERVICE_Response@: " + (LocalTime.now() + " SERVICE_Response time-ms : " + (System.currentTimeMillis() - serviceTime.get())));
	}
}
