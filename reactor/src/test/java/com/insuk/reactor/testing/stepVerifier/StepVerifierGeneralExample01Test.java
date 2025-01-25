package com.insuk.reactor.testing.stepVerifier;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class StepVerifierGeneralExample01Test {
    @Test
    public void sayHelloReactorTest() {
        StepVerifier.create(Mono.just("Hello Reactor")) // create()을 통해 테스트 대상 Sequence를 생성한다.
                .expectNext("Hello Reactor") // onNext Signal을 통해 전달되는 값이 파라미터로 전달된 값과 같음을 기대한다.
                .expectComplete() // onComplete Signal이 전송되기를 기대한다.
                .verify(); // 검증을 트리거 한다.
    }
}
