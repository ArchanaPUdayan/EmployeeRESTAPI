package com.example.springrestapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.springrestapi.model.Employee;
import com.example.springrestapi.repository.EmployeeRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository empRepository;

	@Override
	public List<Employee> getEmployees(int pageNumber,int pageSize) {
		Pageable pages = PageRequest.of(pageNumber, pageSize,Direction.DESC,"id");
		return empRepository.findAll(pages).getContent(); //getcontent heps to convert to list
	}


	@Override
	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return empRepository.save(employee);
		
	}

	@Override
	public Employee getSingleEmployeeById(int id) {
		// TODO Auto-generated method stub
		Optional<Employee> emp=empRepository.findById(id);
		if(emp.isPresent()) {
			return emp.get();
		}
		throw new RuntimeException("Employee is not found for the id"+id);
	}

	@Override
	public void deleteEmployee(int id) {
		empRepository.deleteById(id);
		
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return empRepository.save(employee);
	}

	@Override
	public List<Employee> getEmployeesByName(String name) {
	
		return empRepository.findByName(name);
	}

	@Override
	public List<Employee> getEmployeesByNameAndLocation(String name, String location) {
		// TODO Auto-generated method stub
		return empRepository.findByNameAndLocation(name, location);
	}

	@Override
	public List<Employee> getEmployeesByNameKeyword(String keyword) {
		Sort sort=Sort.by(Sort.Direction.ASC,"id");
		return empRepository.findByNameContaining(keyword,sort);
	}


	@Override
	public List<Employee> getEmployeeByNameOrLocation(String name, String location) {
		// TODO Auto-generated method stub
		return empRepository.getEmployeeNameOrLocation(name, location);
	}


	@Override
	public Integer deleteEmployeeByName(String name) {
		// TODO Auto-generated method stub
		return empRepository.deleteEmployeeByName(name);
	}

}
