package com.rollingstone.debugdemorestapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.rollingstone.debugdemorestapi.dao.EmployeeRepository;
import com.rollingstone.debugdemorestapi.model.Department;
import com.rollingstone.debugdemorestapi.model.Employee;

@Service
public class EmployeeService {

	private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	private EmployeeRepository employeeRepository;
	


	public EmployeeService() {
	}

	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Employee getEmployee(long id) {
		Optional<Employee> employeeOptional = employeeRepository.findById(id);
		Employee dept = employeeOptional.get();
		return dept;
	}

	public Employee updateEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
	}

	public Page<Employee> getAllEmployeeByPage(Integer page, Integer size) {
		Page pageOfCustomers = employeeRepository.findAll(new PageRequest(page, size));
		// example of adding to the /metrics
		
		return pageOfCustomers;
	}

	public List<Employee> getAllEmployees() {
		Iterable<Employee> pageOfEmployees = employeeRepository.findAll();
		List<Employee> employees = new ArrayList<Employee>();
		for (Employee employee : pageOfEmployees) {
			employees.add(employee);
		}
		log.info("In Real Service getAllCustomers size :" + employees.size());

		return employees;
	}
	
	public List<Employee> getAllEmployeesByDepartment(Department department) {
		Iterable<Employee> pageOfEmployees = employeeRepository.findByDepartment(department);
		List<Employee> employees = new ArrayList<Employee>();
		for (Employee employee : pageOfEmployees) {
			employees.add(employee);
		}
		log.info("In Real Service getAllCustomers size :" + employees.size());

		return employees;
	}
}
