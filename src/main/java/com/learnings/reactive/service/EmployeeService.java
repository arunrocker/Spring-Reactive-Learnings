package com.learnings.reactive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnings.reactive.dao.EmployeeDao;
import com.learnings.reactive.dto.Employee;

import reactor.core.publisher.Flux;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	public List<Employee> getEmployees() throws InterruptedException{
		long start = System.currentTimeMillis();
		List<Employee> list = employeeDao.getEmployees();
		long end = System.currentTimeMillis();
		System.out.println("Execution Completed time"+(end-start));
		return list;
	}
	public Flux<Employee> getEmployeesStream() {
		long start = System.currentTimeMillis();
		Flux<Employee> list = employeeDao.getEmployeesStream();
		long end = System.currentTimeMillis();
		System.out.println("Execution Completed time"+(end-start));
		return list;
	}
}
