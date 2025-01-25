package com.insuk.reactor.testing.stepVerifier;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class StepVerifierGeneralExample02Test {

    @Test
    public void sayHelloTest() {
        StepVerifier.create(GeneralTestExample.sayHello())
                .expectSubscription()
                .as("# expect subscription")
                .expectNext("Hi")
                .as("# expect Hi")
                // expectation "# expect Hi" failed (expected value: Hi; actual value: Hello)
                .expectNext("Reactor")
                .as("# expect Reactor")
                .verifyComplete();
    }

    @Test
    public void divideByTwoTest() {
        Flux<Integer> source = Flux.just(2, 4, 6, 8, 10);
        StepVerifier.create(GeneralTestExample.divideByTwo(source))
                .expectSubscription()
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
                .expectError()
                .verify();
    }

    @Test
    public void takeNumberTest() {
        Flux<Integer> source = Flux.range(0, 1000);
        StepVerifier.create(
                GeneralTestExample.takeNumber(source, 500),
                StepVerifierOptions.create().scenarioName("Verify from 0 to 498")
        )
                .expectSubscription() // expectSubscription()으로 구독이 발생했음을 기대합니다.
                .expectNext(0) // 숫자 0이 emit 되었음을 기대합니다.
                .expectNextCount(498) // 498개의 숫자가 emit 되었음을 기대합니다.
                .expectNext(500) // [Verify from 0 to 498] expectation "expectNext(500)" failed (expected value: 500; actual value: 499)
                .expectComplete()
                .verify();
    }

    public static class GeneralTestExample {
        public static Flux<String> sayHello() {
            return Flux.just("Hello", "Reactor");
        }

        public static Flux<Integer> divideByTwo(Flux<Integer> source) {
            return source.zipWith(Flux.just(2, 2, 2, 2, 0), (x, y) -> x / y);
        }

        public static Flux<Integer> takeNumber(Flux<Integer> source, long n) {
            return source.take(n);
        }
    }
}
