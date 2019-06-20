package com.employee.employeeManagement.employeeRepository;

import org.springframework.data.repository.CrudRepository;

import com.employee.employeeManagement.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

	public Employee findByEmplId(int id);
}
