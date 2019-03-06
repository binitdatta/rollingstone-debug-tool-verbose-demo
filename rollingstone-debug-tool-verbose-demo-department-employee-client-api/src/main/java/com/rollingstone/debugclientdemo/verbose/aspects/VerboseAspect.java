package com.rollingstone.debugclientdemo.verbose.aspects;

import java.util.UUID;

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

import com.rollingstone.debugclientdemo.model.EmployeeResponse;
import com.rollingstone.debugclientdemo.model.verbose.RequestInsightCollector;

@Aspect
@Component
public class VerboseAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Before("execution(public * com.rollingstone.debugclientdemo.controller.EmployeeServiceRESTClientController.getEmployeesByDepartment*(..))")
	public void logBeforeRestCallgetEmployeesByDepartment(JoinPoint pjp) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();

		boolean verbose = Boolean.valueOf(request.getParameter("verbose"));
		logger.info("Verbose :" + verbose);
		RequestInsightCollector.initializeRequest();
		RequestInsightCollector.setVerbose(verbose);
		logger.info(":::::AOP Before REST call:::::" + pjp);
	}

	@AfterReturning("execution(public * com.rollingstone.debugclientdemo.controller.EmployeeServiceRESTClientController.getEmployeesByDepartment*(..))")
	public void afterCallinggetEmployeesByDepartment(JoinPoint pjp) {
		logger.info(":::::AOP @AfterReturning Create REST call:::::" + pjp);
		RequestInsightCollector.releaseRequest();
	}

	@Before("execution(* com.rollingstone.debugclientdemo.service.EmployeeServiceHelper.getDepartment(..))")
	public void logBeforeEmployeeServicegetDepartment(JoinPoint pjp) throws Throwable {

		logger.info(":::::AOP Before REST call::::: logBeforeEmployeeServicegetDepartment" + pjp);
	
		String requestID = UUID.randomUUID().toString();
		RequestInsightCollector.startRequest(requestID);
		RequestInsightCollector.addRequestIdToMap("DEPARTMENT", requestID);
	}

	@Before("execution(* com.rollingstone.debugclientdemo.service.EmployeeServiceHelper.getEmployeesForDepartment(..))")
	public void logBeforeEmployeeServicegetEmployeesForDepartmentt(JoinPoint pjp) throws Throwable {

		logger.info(":::::AOP Before REST call::::: logBeforeEmployeeServicegetEmployeesForDepartmentt" + pjp);

		String requestID = UUID.randomUUID().toString();
		RequestInsightCollector.startRequest(requestID);
		RequestInsightCollector.addRequestIdToMap("EMPLOYEE", requestID);
	}
	
	@Before("execution(* com.rollingstone.debugclientdemo.service.*.*(..))")
	public void before(JoinPoint joinPoint){
		//Advice
		logger.info(" Check for user access ");
		logger.info(" Allowed execution for {}", joinPoint);
	}

	
}
