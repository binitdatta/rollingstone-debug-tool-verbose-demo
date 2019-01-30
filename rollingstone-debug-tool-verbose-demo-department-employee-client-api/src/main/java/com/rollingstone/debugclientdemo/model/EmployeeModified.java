package com.rollingstone.debugclientdemo.model;

public class EmployeeModified {

	private String emploeeNumber;
	private String employeeName;
	private String title;
	private String employeeOfficialName;
	
	 
	public String getEmploeeNumber() {
		return emploeeNumber;
	}
	public void setEmploeeNumber(String emploeeNumber) {
		this.emploeeNumber = emploeeNumber;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEmployeeOfficialName() {
		return employeeOfficialName;
	}
	public void setEmployeeOfficialName(String employeeOfficialName) {
		this.employeeOfficialName = employeeOfficialName;
	}
	public EmployeeModified(String emploeeNumber, String employeeName, String title, String employeeOfficialName) {
		super();
		this.emploeeNumber = emploeeNumber;
		this.employeeName = employeeName;
		this.title = title;
		this.employeeOfficialName = employeeOfficialName;
	}
	@Override
	public String toString() {
		return "EmployeeResponse [emploeeNumber=" + emploeeNumber + ", employeeName=" + employeeName + ", title="
				+ title + ", employeeOfficialName=" + employeeOfficialName + "]";
	}
	public EmployeeModified() {
		super();
	}
	
	
}
