package com.learnings.reactive.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.learnings.reactive.dao.EmployeeDao;
import com.learnings.reactive.dto.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeHandler {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	public Mono<ServerResponse> getEmployees(ServerRequest request){
		long start = System.currentTimeMillis();
		List<Employee> list = null;
		try {
			list = employeeDao.getEmployees();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("Execution Completed time"+(end-start));
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(list,Employee.class);
	}
	public Mono<ServerResponse> getEmployeesStream(ServerRequest request) {
		long start = System.currentTimeMillis();
		Flux<Employee> list = employeeDao.getEmployeesStream();
		long end = System.currentTimeMillis();
		System.out.println("Execution Completed time"+(end-start));
		return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(list,Employee.class);
	}
	public Mono<ServerResponse> getEmployeeId(ServerRequest request) {
		String employeeId = request.pathVariable("input");
		Mono<Employee> mono = null;
		try {
			mono = employeeDao.getEmployeeById(employeeId);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return ServerResponse.ok().body(mono,Employee.class);
	}
	public Mono<ServerResponse> saveEmployee(ServerRequest request) {
		Mono<Employee> mono = request.bodyToMono(Employee.class);
		Flux<Employee> flux = employeeDao.saveEmployee(mono); 
		return ServerResponse.ok().body(flux,Employee.class);
	}
}
