package com.graphite.demo.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.codahale.metrics.Timer.Context;

@Aspect
@Component
public class TimedAspect {

	@Autowired
	private MetricRegistry metricsRegistry;
	
	@Around("@annotation(Timed)")
	public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
		String className = extractClassname(joinPoint);
		String methodName = extractMethodName(joinPoint);
		Timer timer = metricsRegistry.timer(className+"."+methodName);
		
		Context timerContext = timer.time();
		Object proceed = joinPoint.proceed();
		timerContext.stop();
		return proceed;
	}

	private String extractMethodName(ProceedingJoinPoint joinPoint) {
		return ((MethodSignature) joinPoint.getSignature()).getMethod().getName();
	}

	private String extractClassname(ProceedingJoinPoint joinPoint) {
		return ((MethodSignature) joinPoint.getSignature()).getMethod().getDeclaringClass().getSimpleName();
	}

}
