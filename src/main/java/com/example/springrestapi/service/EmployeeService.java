package com.example.springrestapi.service;

import java.util.List;

import com.example.springrestapi.model.Employee;

public interface EmployeeService {
	List<Employee> getEmployees(int pageNumber,int pageSize);
	Employee saveEmployee(Employee employee);
	
	Employee getSingleEmployeeById(int id);
	Employee updateEmployee(Employee employee);
	void deleteEmployee(int id);
	List<Employee> getEmployeesByName(String name);
	List<Employee> getEmployeesByNameAndLocation(String name,String location);
	List<Employee> getEmployeesByNameKeyword(String keyword);
	
	List<Employee> getEmployeeByNameOrLocation(String name,String location);
	Integer deleteEmployeeByName(String name);

}
