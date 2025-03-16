package com.learnings.reactive.dao;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.learnings.reactive.dto.Employee;

import reactor.core.publisher.Flux;

@Component
public class EmployeeDao {

	public List<Employee> getEmployees() throws InterruptedException{
		return IntStream.rangeClosed(1, 50).peek(EmployeeDao::sleepCurrentExecution).peek(i->System.out.println("Processing Count"+i)).mapToObj(i-> new Employee(i, "Employee"+i)).collect(Collectors.toList());
	}
	private static void sleepCurrentExecution(int i) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Flux<Employee> getEmployeesStream() {
		return Flux.range(1, 50).delayElements(Duration.ofSeconds(1)).doOnNext(i->System.out.println("Processing Count" + i)).map(i-> new Employee(i, "Employee"+i));
	}
}
