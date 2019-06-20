package com.employee.employeeManagement.employeeController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.employee.employeeManagement.employeeExceptions.EmployeeNotFoundException;
import com.employee.employeeManagement.employeeService.EmployeeService;
import com.employee.employeeManagement.entity.Employee;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	// Service for Adding Employee
	@RequestMapping(method = RequestMethod.POST, value = "/employee")
	public void addEmployee(@RequestBody Employee employee) throws Exception {
		try {
			employeeService.addEmployeeDetails(employee);
		} catch (Exception e) {
			throw new Exception("Problem in adding Employee details");
		}
	}

	// Service for Deleting Employee
	@RequestMapping(method = RequestMethod.DELETE, value = "/employee/deleteEmployee/{id}")
	public void deleteEmployeeById(@PathVariable int id) throws Exception {
		try {
			employeeService.deleteEmployee(id);
		} catch (Exception e) {
			throw new Exception("Problem in deleting Employee details");
		}
	}

	// Service for getting list of employees
	@RequestMapping(method = RequestMethod.GET, value = "/employee/getEmployees")
	public List<Employee> getAllEmployees() throws Exception {
		List<Employee> employees = null;
		if (!employeeService.getAllEmployees().isEmpty()) {
			employees = employeeService.getAllEmployees();
		} else {
			throw new EmployeeNotFoundException("Employees not available");
		}
		return employees;
	}

	// Service for getting employee details by EmpId
	@RequestMapping(method = RequestMethod.GET, value = "/employee/getEmployee/{id}")
	public Employee getEmployeeById(@PathVariable int id) {
		Employee employee = null;
		if (employeeService.getEmployeeById(id) != null) {
			employee = employeeService.getEmployeeById(id);
		} else {
			throw new EmployeeNotFoundException("Employee Id not available");
		}
		return employee;
	}

	// Service for checking valid employee
	@RequestMapping(method = RequestMethod.GET, value = "/employee/{id}")
	public String validateEmployeeById(@PathVariable int id) {
		if (employeeService.getEmployeeById(id) != null) {
			return "Its a valid Employee";
		} else {
			throw new EmployeeNotFoundException("Employee Id not available");
		}
	}
}
