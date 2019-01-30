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

import com.rollingstone.debugdemorestapi.dao.DepartmentRepository;
import com.rollingstone.debugdemorestapi.model.Department;

@Service
public class DepartmentService {

private static final Logger log = LoggerFactory.getLogger(DepartmentService.class);
	
	@Autowired
	private DepartmentRepository departmentRepository;
	


	public DepartmentService() {
	}

	public Department createDepartment(Department department) {
		return departmentRepository.save(department);
	}

	public Department getDepartment(long id) {
		Optional<Department> departmentOptional = departmentRepository.findById(id);
		Department dept = departmentOptional.get();
		return dept;
	}

	public Department updateDepartment(Department department) {
		return departmentRepository.save(department);
	}

	public void deleteDepartment(Long id) {
		departmentRepository.deleteById(id);
	}

	public Page<Department> getAllDepartmentByPage(Integer page, Integer size) {
		Page pageOfCustomers = departmentRepository.findAll(new PageRequest(page, size));
		// example of adding to the /metrics
		
		return pageOfCustomers;
	}

	public List<Department> getAllDepartments() {
		Iterable<Department> pageOfDepartments = departmentRepository.findAll();
		List<Department> departments = new ArrayList<Department>();
		for (Department department : pageOfDepartments) {
			departments.add(department);
		}
		log.info("In Real Service getAllCustomers size :" + departments.size());

		return departments;
	}

}
