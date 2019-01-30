package com.rollingstone.debugdemorestapi.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "rollingstone_department")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="department_number", nullable = false)
	private String departmentNumber;
	
	@Column(name="department_name", nullable = false)
	private String departmentName;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
	@JsonIgnore
	private Set<Employee> employees = new HashSet<Employee>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDepartmentNumber() {
		return departmentNumber;
	}

	public void setDepartmentNumber(String departmentNumber) {
		this.departmentNumber = departmentNumber;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Department(long id, String departmentNumber, String departmentName, Set<Employee> employees) {
		super();
		this.id = id;
		this.departmentNumber = departmentNumber;
		this.departmentName = departmentName;
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", departmentNumber=" + departmentNumber + ", departmentName=" + departmentName
				+ ", employees=" + employees + "]";
	}

	public Department() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((departmentName == null) ? 0 : departmentName.hashCode());
		result = prime * result + ((departmentNumber == null) ? 0 : departmentNumber.hashCode());
		result = prime * result + ((employees == null) ? 0 : employees.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Department other = (Department) obj;
		if (departmentName == null) {
			if (other.departmentName != null)
				return false;
		} else if (!departmentName.equals(other.departmentName))
			return false;
		if (departmentNumber == null) {
			if (other.departmentNumber != null)
				return false;
		} else if (!departmentNumber.equals(other.departmentNumber))
			return false;
		if (employees == null) {
			if (other.employees != null)
				return false;
		} else if (!employees.equals(other.employees))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	
	
	
}
