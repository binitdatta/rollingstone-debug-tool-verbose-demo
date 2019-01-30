package com.rollingstone.debugclientdemo.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rollingstone.debugclientdemo.model.EmployeeResponse;
import com.rollingstone.debugclientdemo.model.verbose.RequestInsightCollector;
import com.rollingstone.debugclientdemo.service.EmployeeService;


@RestController
public class EmployeeServiceRESTClientController {

	
	Logger logger  = LoggerFactory.getLogger(EmployeeServiceRESTClientController.class);
	
		@Autowired
		EmployeeService employeeService;
	
	   @RequestMapping(value = "/rollingstone-verbose-demo/client/department/{departmentid}/employees", //
	            method = RequestMethod.GET, //
	            produces = { MediaType.APPLICATION_JSON_VALUE, //
	                    MediaType.APPLICATION_XML_VALUE })
	    @ResponseBody
	    public EmployeeResponse getEmployeesByDepartment(@PathVariable("departmentid") long departmentid, HttpServletRequest request) {
		   boolean verbose = Boolean.valueOf(request.getParameter("verbose"));
		 
		   logger.info("Verbose :"+verbose);
			RequestInsightCollector.setVerbose(verbose);
			
		   EmployeeResponse list = employeeService.getEmployeesByDepartment(departmentid, verbose);
		   RequestInsightCollector.releaseRequest();
	        return list;
	    }
}
