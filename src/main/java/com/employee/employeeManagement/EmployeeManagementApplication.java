package com.employee.employeeManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.employee.employeeManagement.employeeRepository.EmployeeRepository;
import com.employee.employeeManagement.swaggerConfig.SwaggerConfig;

@SpringBootApplication
@Import(SwaggerConfig.class)
@EnableJpaRepositories(basePackageClasses= {EmployeeRepository.class})
public class EmployeeManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementApplication.class, args);
	}

}
