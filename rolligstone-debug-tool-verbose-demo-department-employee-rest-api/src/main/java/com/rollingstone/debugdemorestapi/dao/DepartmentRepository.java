package com.rollingstone.debugdemorestapi.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.rollingstone.debugdemorestapi.model.Department;

public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long> {

	Page findAll(Pageable pageable);
}

