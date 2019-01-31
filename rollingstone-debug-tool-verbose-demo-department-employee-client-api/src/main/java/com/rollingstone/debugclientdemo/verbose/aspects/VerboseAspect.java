package com.rollingstone.debugclientdemo.verbose.aspects;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.rollingstone.debugclientdemo.model.verbose.RequestInsightCollector;

@Aspect
@Component
public class VerboseAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Before("execution(public * com.rollingstone.debugclientdemo.controller.EmployeeServiceRESTClientController.getEmployeesByDepartment*(..))")
	public void logBeforeRestCall(JoinPoint pjp) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

		 boolean verbose = Boolean.valueOf(request.getParameter("verbose"));
		 logger.info("Verbose :"+verbose);
			RequestInsightCollector.setVerbose(verbose);
		logger.info(":::::AOP Before REST call:::::" + pjp);
	}

	@AfterReturning("execution(public * com.rollingstone.debugclientdemo.controller.EmployeeServiceRESTClientController.getEmployeesByDepartment*(..))")
	public void afterCallingCreatePerson(JoinPoint pjp) {
		logger.info(":::::AOP @AfterReturning Create REST call:::::" + pjp);
		RequestInsightCollector.releaseRequest();
	}
}
