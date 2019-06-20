package com.employee.employeeManagement.employeeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.employeeManagement.employeeRepository.EmployeeRepository;
import com.employee.employeeManagement.entity.Employee;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	//Add Employee
	public void addEmployeeDetails(Employee employee) {
		employeeRepository.save(employee);
	}
	
	//Delete Employee
	public void deleteEmployee(int id) {
		employeeRepository.deleteById(id);
	}
	
	//Get list of employees
	public List<Employee> getAllEmployees(){
		List<Employee> employees = new ArrayList<>();
		employeeRepository.findAll().forEach(employees::add);
		return employees;
	}
	
	//Get employee details by EmpId
	public Employee getEmployeeById(int id) {
	 return employeeRepository.findByEmplId(id);
	}
	
	
	//Check valid employee


}
