package com.indices.loggers;

import java.time.LocalTime;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class LogError {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Pointcut("execution(* com.indices.*.*.*(..))")
	public void pointcut() {
		//method left empty
	}
	
	@AfterThrowing(pointcut = "pointcut()", throwing = "ex")
	public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex) {
		log.error("Exception throwing: After running " + joinPoint.getSignature().getName() + "() @ " + LocalTime.now());
		log.error("********** " + ex.getMessage() + " Excepiton occured during " + joinPoint.toShortString(), ex);
		
	}

}
