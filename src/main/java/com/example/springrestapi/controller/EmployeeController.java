package com.example.springrestapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springrestapi.model.Employee;
import com.example.springrestapi.repository.EmployeeRepository;
import com.example.springrestapi.service.EmployeeService;

import jakarta.validation.Valid;



//@Controller
@RestController
@RequestMapping("api/v1")
public class EmployeeController {
	
	//localhost:8080/api/v1/employees
	@Autowired
	private EmployeeService eService;
	
	@Autowired
	private EmployeeRepository eRepo;
	
	
	
	//ResponseEntity---->StatusCode
	
//	@RequestMapping(value="/employees", method=RequestMethod.GET)
//	@ResponseBody
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getEmployees(@RequestParam Integer pageNumber,@RequestParam Integer pageSize) {
		return new ResponseEntity<List<Employee>>(eService.getEmployees(pageNumber,pageSize),HttpStatus.OK);
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") int id) {
		return new ResponseEntity<Employee>(eService.getSingleEmployeeById(id),HttpStatus.OK);
		
	}
	//localhost:8080/employees?id=34
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") int id) {
		eService.deleteEmployee(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}

	//@RequestBody conversts from JSON to Object
	
	@PostMapping("/employees")
	public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee) {
	    return new ResponseEntity<Employee>(eService.saveEmployee(employee),HttpStatus.CREATED);
	}

	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable int id,@RequestBody Employee employee) {
		employee.setId(id);
		return new ResponseEntity<Employee>(eService.updateEmployee(employee),HttpStatus.OK);
		
	}
	@GetMapping("/employees/filterByName")
	public ResponseEntity<List<Employee>> getEmployeesByName(@RequestParam String name){
		return new ResponseEntity<List<Employee>> (eService.getEmployeesByName(name),HttpStatus.OK);
		
	}
	@GetMapping("/employees/filterByNameAndLocation")
	public ResponseEntity<List<Employee>> getEmployeeByNameAndLocation(@RequestParam String name,@RequestParam String location){
		return new ResponseEntity<List<Employee>>(eService.getEmployeesByNameAndLocation(name, location),HttpStatus.OK);
	}
	
	@GetMapping("/employees/findByKeyword")
	public ResponseEntity<List<Employee>> getEmployeeByNameAndKeyword(@RequestParam  String name){
		return new ResponseEntity<List<Employee>>(eService.getEmployeesByNameKeyword(name),HttpStatus.OK);
		
	}
	
	@GetMapping("/employees/{name}/{location}")
	public ResponseEntity<List<Employee>> getEmployeeByNameOrKeyword(@PathVariable  String name, @PathVariable String location){
		return new ResponseEntity<List<Employee>>(eService.getEmployeeByNameOrLocation(name, location),HttpStatus.OK);
		
	}
	
	@DeleteMapping("/employees/delete/{name}")
	public ResponseEntity<Integer> deleteEmployeeByName(@PathVariable String name){
		return new ResponseEntity<Integer> (eService.deleteEmployeeByName(name),HttpStatus.OK);
		
	}

}


