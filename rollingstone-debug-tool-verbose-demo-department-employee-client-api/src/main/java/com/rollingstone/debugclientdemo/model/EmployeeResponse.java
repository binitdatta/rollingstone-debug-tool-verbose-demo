package com.rollingstone.debugclientdemo.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rollingstone.debugclientdemo.model.verbose.RequestInsight;

@JsonInclude(Include.NON_NULL)
public class EmployeeResponse {

	private List<EmployeeModified> employees;
	private Department department;
	@JsonInclude(Include.NON_EMPTY)	
	private List<RequestInsight> requestInsignts = new ArrayList<RequestInsight>();
	
	public EmployeeResponse(List<EmployeeModified> employees, List<RequestInsight> requestInsignts) {
		super();
		this.employees = employees;
		this.requestInsignts = requestInsignts;
	}
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<EmployeeModified> getEmployees() {
		return employees;
	}
	public void setEmployees(List<EmployeeModified> employees) {
		this.employees = employees;
	}
	
	
	public List<RequestInsight> getRequestInsignts() {
		return requestInsignts;
	}

	public void setRequestInsignts(List<RequestInsight> requestInsignts) {
		this.requestInsignts = requestInsignts;
	}

	public EmployeeResponse() {
		super();
	}

	@Override
	public String toString() {
		return "EmployeeResponse [employees=" + employees + ", department=" + department + ", requestInsignts=" + requestInsignts + "]";
	}
	
	
}
