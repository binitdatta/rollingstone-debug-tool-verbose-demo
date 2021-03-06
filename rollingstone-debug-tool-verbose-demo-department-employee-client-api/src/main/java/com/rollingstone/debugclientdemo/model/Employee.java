package com.rollingstone.debugclientdemo.model;



public class Employee {


	private long id;
	
	private String emploeeNumber;
	
	private String employeeName;
	
	private String title;
	
	Department department;
	
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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Employee(long id, String emploeeNumber, String employeeName, String title, Department department) {
		super();
		this.id = id;
		this.emploeeNumber = emploeeNumber;
		this.employeeName = employeeName;
		this.title = title;
		this.department = department;
	}
	public Employee() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((emploeeNumber == null) ? 0 : emploeeNumber.hashCode());
		result = prime * result + ((employeeName == null) ? 0 : employeeName.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (emploeeNumber == null) {
			if (other.emploeeNumber != null)
				return false;
		} else if (!emploeeNumber.equals(other.emploeeNumber))
			return false;
		if (employeeName == null) {
			if (other.employeeName != null)
				return false;
		} else if (!employeeName.equals(other.employeeName))
			return false;
		if (id != other.id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", emploeeNumber=" + emploeeNumber + ", employeeName=" + employeeName + ", title="
				+ title + ", department=" + department + "]";
	}

	
}
