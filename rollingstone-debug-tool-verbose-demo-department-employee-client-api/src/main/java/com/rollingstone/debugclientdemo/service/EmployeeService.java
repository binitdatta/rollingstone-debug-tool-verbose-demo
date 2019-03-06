package com.rollingstone.debugclientdemo.service;

import org.springframework.stereotype.Service;

import com.rollingstone.debugclientdemo.model.EmployeeResponse;
import com.rollingstone.debugclientdemo.model.verbose.RequestInsightCollector;

@Service
public class EmployeeService {

	EmployeeServiceHelper employeeServiceHelper;

	EmployeeService(EmployeeServiceHelper employeeServiceHelper){
		this.employeeServiceHelper =employeeServiceHelper;
	}

	public EmployeeResponse getEmployeesByDepartment(long departmentId) {

		EmployeeResponse response = new EmployeeResponse();
		response.setDepartment(employeeServiceHelper.getDepartment(departmentId));
		response.setEmployees(employeeServiceHelper.getEmployeesForDepartment(departmentId));

		if (RequestInsightCollector.isVerbose()) {
			response.setRequestInsignts(RequestInsightCollector.getRequestInsights());
		}

		return response;
	}



}
