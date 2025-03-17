package com.learnings.reactive.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.learnings.reactive.handler.EmployeeHandler;

@Configuration
public class RouterConfig {

	@Autowired
	private EmployeeHandler handler;
	@Bean
	public RouterFunction<ServerResponse> router(){
		return RouterFunctions.route().GET("/router/stream",handler::getEmployeesStream)
				.GET("/router/list",handler::getEmployees)
				.GET("router/id/{input}",handler::getEmployeeId)
				.GET("router/save",handler::saveEmployee).build();
	}
}
