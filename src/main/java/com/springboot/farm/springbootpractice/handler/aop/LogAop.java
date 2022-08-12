package com.springboot.farm.springbootpractice.handler.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAop {
	
	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Pointcut("@annotation(com.springboot.farm.springbootpractice.handler.aop.annotation.Log)")
	public void enableLog() {}
	
	@Around("enableLog()")
	public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
		
		Map<String, Object> argsMap = getArgs(joinPoint);
		
		
		LOGGER.info(">>>>>>>>>>> Method Call: {} = {} <<<<<<<<<<<<",
				joinPoint.getSignature().getName(),
				argsMap);
		
		Object result = joinPoint.proceed();	
		
		LOGGER.info(">>>>>>>>>>> Method End: {} = {} <<<<<<<<<<<<",
				joinPoint.getSignature().getName(),
				result);
		
		return result;
	
	}
	
	private Map<String, Object> getArgs(ProceedingJoinPoint joinPoint) {
		Map<String, Object> argsMap = new HashMap<String, Object>();
		
		CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
		Object[] args = joinPoint.getArgs();
		String[] argNames = codeSignature.getParameterNames();
		
		for(int i = 0; i < argNames.length; i++) {
			argsMap.put(argNames[i], args[i]);
		}
		
		return argsMap;
	}
}