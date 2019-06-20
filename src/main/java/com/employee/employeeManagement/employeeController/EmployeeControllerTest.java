package com.employee.employeeManagement.employeeController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.employee.employeeManagement.AbstractTest;
import com.employee.employeeManagement.entity.Employee;


@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeControllerTest extends AbstractTest {
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void getAllEmployees() throws Exception {
		String uri = "/employee/getEmployees";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Employee[] productlist = super.mapFromJson(content, Employee[].class);
		assertTrue(productlist.length > 0);
	}

	@Test
	public void addEmployee() throws Exception {
		String uri = "/employee";
		Employee employee = new Employee();
		employee.setEmplId(1);
		employee.setFullName("Billa");
		employee.setUsername("Bil");
		employee.setAnswer("Chennai");
		employee.setSecurityQuestion("Place of Birth");
		employee.setDateOfBirth(new Date());
		employee.setEmailId("a@gmail.com");
		employee.setGender("Male");

		String inputJson = super.mapToJson(employee);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Employee is created successfully");
	}

	@Test
	public void deleteEmployeeById() throws Exception {
		String uri = "/employee/deleteEmployee/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Employee is deleted successsfully");
	}
}
