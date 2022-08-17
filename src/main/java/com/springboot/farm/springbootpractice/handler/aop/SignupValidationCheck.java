package com.springboot.farm.springbootpractice.handler.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import com.springboot.farm.springbootpractice.handler.exception.CustomValidationApiException;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class SignupValidationCheck {
	
	@Pointcut("@annotation(com.springboot.farm.springbootpractice.handler.aop.annotation.ValidCheck)")
	private void enableValidCheck() {};
	
	@Before(value = "enableValidCheck()")
	public void validCheck(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		
		for(Object arg : args) {
			if(arg.getClass() == BeanPropertyBindingResult.class) {
				BindingResult bindingResult = (BindingResult) arg;
				
				Map<String, String> errorMap = new HashMap<String, String>();
				if(bindingResult.hasErrors()) {
					
					bindingResult.getFieldErrors()
						.forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
					
					log.error(">>>>> 유효성 검사 실패: {}", errorMap);
					throw new CustomValidationApiException("유효성 검사 실패", errorMap);
				}
				
			}
		}
		
	}
	
	@AfterReturning(value = "enableValidCheck()", returning = "returnObj")
	public void validCheckAfter(JoinPoint joinPoint, Object returnObj) {
		log.info(">>>>>> 결과: {}", returnObj);
	}
}