package com.rollingstone.debugclientdemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rollingstone.debugclientdemo.model.Department;
import com.rollingstone.debugclientdemo.model.Employee;
import com.rollingstone.debugclientdemo.model.EmployeeModified;
import com.rollingstone.debugclientdemo.model.EmployeeResponse;
import com.rollingstone.debugclientdemo.model.verbose.RequestInsight;
import com.rollingstone.debugclientdemo.model.verbose.RequestInsightCollector;

@Service
public class EmployeeService {

	static final String URL_DEPARTMENT = "http://localhost:9094/rollingstone-verbose-demo/department";
	static final String URL_DEPARTMENT_EMPLOYEES = "http://localhost:9094/rollingstone-verbose-demo/department/";

	static final String URL_EMPLOYEES_XML = "http://localhost:8080/employees.xml";
	static final String URL_EMPLOYEES_JSON = "http://localhost:8080/employees.json";

	RestTemplate restTemplate = new RestTemplate();

	public EmployeeResponse getEmployeesByDepartment(long departmentId) {

		EmployeeResponse response = new EmployeeResponse();
		response.setDepartment(getDepartment(departmentId, RequestInsightCollector.isVerbose()));
		response.setEmployees(getEmployeesForDepartment(departmentId, RequestInsightCollector.isVerbose()));
		if (RequestInsightCollector.isVerbose()) {
			response.setRequestInsignts(RequestInsightCollector.getRequestInsights());
		}
		return response;
	}

	private Department getDepartment(long departmentId, boolean verbose) {
		String requestID = UUID.randomUUID().toString();
		RequestInsightCollector.startRequest(requestID);

		// Department department =
		// restTemplate.getForObject(URL_DEPARTMENT+"/"+departmentid, Department.class);
		ResponseEntity<Department> departmentEntity = restTemplate.getForEntity(URL_DEPARTMENT + "/" + departmentId,
				Department.class);
		ResponseEntity<String> responseStr = restTemplate.getForEntity(URL_DEPARTMENT + "/" + departmentId,
				String.class);

		Department department = (Department) departmentEntity.getBody();
		if (verbose) {
			RequestInsight requestInsight = RequestInsightCollector.getRequestInsight(requestID);
			if (requestInsight != null && requestInsight.isVerbose()) {
				requestInsight.setRequestID(requestID);
				requestInsight.setRequestResponse(responseStr.getBody().toString());
				requestInsight.setRequestStatus(String.valueOf(responseStr.getStatusCodeValue()));
				requestInsight.setRequestURI(URL_DEPARTMENT + "/" + departmentId);
				requestInsight.setElaspsedTime(RequestInsightCollector.getElapsedTime(requestID));
				RequestInsightCollector.addRequestInsight(requestInsight);
			}
		}
		return department;
	}
	
	private List<EmployeeModified> getEmployeesForDepartment(long departmentId, boolean verbose){
		
		String requestID = UUID.randomUUID().toString();
		RequestInsightCollector.startRequest(requestID);
		
		List<EmployeeModified> employeeList = new ArrayList<EmployeeModified>();
		
		//Employee[] list = restTemplate.getForObject(URL_DEPARTMENT_EMPLOYEES + departmentId + "/employees",
				//Employee[].class);
		
		ResponseEntity<Employee[]> employeeListEntity = restTemplate.getForEntity(URL_DEPARTMENT_EMPLOYEES + departmentId + "/employees",
				Employee[].class);
		ResponseEntity<String> employeeListStr = restTemplate.getForEntity(URL_DEPARTMENT_EMPLOYEES + departmentId + "/employees",
				String.class);

		Employee[] empList = (Employee[]) employeeListEntity.getBody();
		
		if (empList != null) {
			for (Employee e : empList) {
				EmployeeModified employeeResponse = new EmployeeModified(e.getEmploeeNumber(), e.getEmployeeName(),
						e.getTitle(), e.getTitle() + " " + e.getEmploeeNumber());

				employeeList.add(employeeResponse);
			}
		}

		if (verbose) {
			
			RequestInsight requestInsight = RequestInsightCollector.getRequestInsight(requestID);
			if (requestInsight != null && requestInsight.isVerbose()) {
				requestInsight.setRequestID(requestID);
				requestInsight.setRequestResponse(employeeListStr.getBody().toString());
				requestInsight.setRequestStatus(String.valueOf(employeeListStr.getStatusCodeValue()));
				requestInsight.setRequestURI(URL_DEPARTMENT_EMPLOYEES + departmentId + "/employees");
				requestInsight.setElaspsedTime(RequestInsightCollector.getElapsedTime(requestID));
				RequestInsightCollector.addRequestInsight(requestInsight);
			}
		}
		return employeeList;
	}

}
