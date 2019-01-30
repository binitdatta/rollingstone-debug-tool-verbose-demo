package com.rollingstone.debugdemorestapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rollingstone.debugdemorestapi.dao.EmployeeDAO;
import com.rollingstone.debugdemorestapi.model.Department;
import com.rollingstone.debugdemorestapi.model.Employee;
import com.rollingstone.debugdemorestapi.service.DepartmentService;
import com.rollingstone.debugdemorestapi.service.EmployeeService;

@RestController

public class HumanResourtceController {
 
    @Autowired
    private EmployeeDAO employeeDAO;
    
    EmployeeService employeeService;
    
    DepartmentService departmentService;
    
    public HumanResourtceController(DepartmentService departmentService, EmployeeService employeeService)
    {
    	this.departmentService = departmentService;
    	this.employeeService = employeeService;
    }
 
    @RequestMapping("/")
    @ResponseBody
    public String welcome() {
        return "Welcome to Rollngstone HumanResource RestTemplate Example.";
    }
 
  
    @RequestMapping(value = "/rollingstone-verbose-demo/employees", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Employee> getEmployees() {
        List<Employee> list = employeeService.getAllEmployees();
        return list;
    }
    
    
 
    @RequestMapping(value = "/rollingstone-verbose-demo/departments", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Department> getDepartments() {
        List<Department> list = departmentService.getAllDepartments();
        return list;
    }

    @RequestMapping(value = "/rollingstone-verbose-demo/department/{departmentNumber}/employees", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Employee> getEmployeesByDepartment(@PathVariable("departmentNumber") long departmentNumber) {
    	Department department  = departmentService.getDepartment(departmentNumber);
    	
        List<Employee> list = employeeService.getAllEmployeesByDepartment(department);
        return list;
    }
 
 
    @RequestMapping(value = "/rollingstone-verbose-demo/employee/{empNo}", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Employee getEmployee(@PathVariable("empNo") long empId) {
        return employeeService.getEmployee(empId);
    }
    
    @RequestMapping(value = "/rollingstone-verbose-demo/department/{departmentnum}", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Department getDepartment(@PathVariable("departmentnum") long departmentnum) {
        return departmentService.getDepartment(departmentnum);
    }
 
   
 
    @RequestMapping(value = "/rollingstone-verbose-demo/employee", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Employee addEmployee(@RequestBody Employee emp) {
 
        System.out.println("(Service Side) Creating employee: " + emp.getEmploeeNumber());
 
        return employeeService.createEmployee(emp);
    }
    
    @RequestMapping(value = "/rollingstone-verbose-demo/department", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Department addDepartment(@RequestBody Department department) {
 
        System.out.println("(Service Side) Creating employee: " + department.getDepartmentName());
 
        return departmentService.createDepartment(department);
    }
 
 
    @RequestMapping(value = "/rollingstone-verbose-demo/employee", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Employee updateEmployee(@RequestBody Employee emp) {
 
        System.out.println("(Service Side) Editing employee: " + emp.getEmploeeNumber());
 
        return employeeService.updateEmployee(emp);
    }
 
    @RequestMapping(value = "/rollingstone-verbose-demo/department", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Department updateDepartment(@RequestBody Department department) {
 
        System.out.println("(Service Side) Editing Department: " + department.getDepartmentName());
 
        return departmentService.updateDepartment(department);
    }
    
    // URL:
    // http://localhost:8080/SomeContextPath/employee/{empNo}
    @RequestMapping(value = "/rollingstone-verbose-demo/employee/{empNo}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public void deleteEmployee(@PathVariable("empNo") long empNo) {
 
        System.out.println("(Service Side) Deleting employee: " + empNo);
 
        employeeService.deleteEmployee(empNo);
    }
 
}