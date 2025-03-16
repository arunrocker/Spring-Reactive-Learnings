package com.learnings.reactive.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnings.reactive.dto.Employee;
import com.learnings.reactive.service.EmployeeService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	@GetMapping("/list")
	public List<Employee> getEmployees() throws InterruptedException{
		return employeeService.getEmployees();
	}
	@GetMapping(value="/stream",produces=MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Employee> getEmployeesStream(){
		return employeeService.getEmployeesStream();
	}
}
