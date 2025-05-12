package com.example.springrestapi.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springrestapi.model.Employee;

import jakarta.transaction.Transactional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>{
	//finder methods to find name based on name column
	List<Employee> findByName(String name);
	
	
	//SELECT * FROM table WHERE name=bushan" and location="India";
	//In this Query we are targetting 2 columns multiple columns consider
	
	List<Employee> findByNameAndLocation(String name,String location);
	
	//SELECT * FROM table WHERE name LIKE "%ram%";
	List<Employee> findByNameContaining(String keyword,Sort sort);
	
	@Query("FROM Employee WHERE name= :name OR location= :location")
	List<Employee> getEmployeeNameOrLocation(String name,String location);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Employee WHERE name= :name")
	Integer deleteEmployeeByName(String name);

}
