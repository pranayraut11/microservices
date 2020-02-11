package com.ecors.product.configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
//@Aspect
public class WebIntercepter {

	/*
	 * @Override public void postHandle(HttpServletRequest request,
	 * HttpServletResponse response, Object handler, ModelAndView modelAndView)
	 * throws Exception { // TODO Auto-generated method stub
	 * System.out.println("In Post handler"); System.out.println(handler);
	 * System.out.println(response.getOutputStream());
	 * 
	 * }
	 */

//	@After(value = "execution(* com.javainuse.service.EmployeeService.*(..)) and args(name,empId)")
//	public void afterAdvice(JoinPoint joinPoint, String name, String empId) {
//		System.out.println("After method:" + joinPoint.getSignature());
//
//		System.out.println("Successfully created Employee with name - " + name + " and id - " + empId);
//	}

}
