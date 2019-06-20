package com.employee.employeeManagement.employeeExceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class EmployeeAlreadyExistException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeAlreadyExistException(String exception) {
		super(exception);
	}
}