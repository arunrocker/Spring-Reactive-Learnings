package com.learnings.reactive;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFlux {

	@Test
	public void testMono() {
		Mono<?> mono = Mono.just("Arun Learnings")
				.then(Mono.error(new RuntimeException("Error:(")))
				.log();
		
		mono.subscribe(System.out::println);
		/*
		 * subscription -> subcribe -> request(n) -> onNext(data) -> onComplete() or onError() 
		 */
	}
	@Test
	public void testFlux() {
		Flux<?> flux = Flux.just("Arun","Kumar","Spring","WebFlux")
				.concatWithValues("AWS")
				.concatWith(Flux.error(new RuntimeException("Flux Error")))
				.log();
		flux.subscribe(System.out::println);
	}
}
