package com.rollingstone.debugclientdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.rollingstone.debugclientdemo.model.Department;
import com.rollingstone.debugclientdemo.model.Employee;
import com.rollingstone.debugclientdemo.model.EmployeeModified;
import com.rollingstone.debugclientdemo.model.verbose.RequestInsightCollector;
import com.rollingstone.debugclientdemo.verbose.interceptors.RestTemplateVerboseInterceptor;

@Service
public class EmployeeServiceHelper {

	public static final String URL_DEPARTMENT = "http://localhost:9094/rollingstone-verbose-demo/department";
	public static final String URL_DEPARTMENT_EMPLOYEES = "http://localhost:9094/rollingstone-verbose-demo/department/";

	
	RestTemplate restTemplate;

	EmployeeServiceHelper() {
		restTemplate = new RestTemplate();
		List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
		if (CollectionUtils.isEmpty(interceptors)) {
			interceptors = new ArrayList<>();
		}
		interceptors.add(new RestTemplateVerboseInterceptor());

		restTemplate.setInterceptors(interceptors);
	}
	
	public Department getDepartment(long departmentId) {

		RequestInsightCollector.addDebugLogMessage("DEPARTMENT","EmployeeServiceHelper","Calling Department API");

		ResponseEntity<Department> departmentEntity = restTemplate.getForEntity(URL_DEPARTMENT + "/" + departmentId,
				Department.class);

		Department department = (Department) departmentEntity.getBody();

		RequestInsightCollector.addDebugLogMessage("DEPARTMENT","EmployeeServiceHelper","Received Department");
		return department;
	}

	public List<EmployeeModified> getEmployeesForDepartment(long departmentId) {
		List<EmployeeModified> employeeList = new ArrayList<EmployeeModified>();

		RequestInsightCollector.addDebugLogMessage("EMPLOYEE","EmployeeServiceHelper","Calling Employee API");

		ResponseEntity<Employee[]> employeeListEntity = restTemplate
				.getForEntity(URL_DEPARTMENT_EMPLOYEES + departmentId + "/employees", Employee[].class);

		Employee[] empList = (Employee[]) employeeListEntity.getBody();

		if (empList != null) {
			for (Employee e : empList) {
				EmployeeModified employeeResponse = new EmployeeModified(e.getEmploeeNumber(), e.getEmployeeName(),
						e.getTitle(), e.getTitle() + " " + e.getEmploeeNumber());

				employeeList.add(employeeResponse);
			}
		}

		RequestInsightCollector.addDebugLogMessage("EMPLOYEE","EmployeeServiceHelper","Received Employees");

		return employeeList;
	}

}
