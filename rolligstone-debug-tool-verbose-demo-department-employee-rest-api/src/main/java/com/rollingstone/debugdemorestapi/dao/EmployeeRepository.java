package com.rollingstone.debugdemorestapi.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.rollingstone.debugdemorestapi.model.Department;
import com.rollingstone.debugdemorestapi.model.Employee;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

	Page findAll(Pageable pageable);
	
	List<Employee> findByDepartment(Department department);
}
