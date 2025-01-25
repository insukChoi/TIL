package com.insuk.reactor.testing.stepVerifier;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.test.StepVerifier;

public class StepVerifierGeneralExample04_BackpressureTest {
    @Test
    public void generateNumberTest() {
        /*
            테스트 대상 클래스의 generateNumber() 메서드는 한 번에 100개의 숫자 데이터를 emit 하는데,
            StepVerifier 의 create() 메서드에서 데이터의 요청 개수를 1로 지정해서 오버플로가 발생했기 때문에 테스트 결과는 'failed'가 됩니다.
         */
        StepVerifier.create(BackpressureTestExample.generateNumber(), 1L)
                .thenConsumeWhile(num -> num >= 1)
                .verifyComplete();
    }

    @Test
    public void generateNumberTest2() {
        StepVerifier.create(BackpressureTestExample.generateNumber(), 1L)
                .thenConsumeWhile(num -> num >= 1)
                .expectError()
                .verifyThenAssertThat() // 검증을 트리거하고 난 후, 추가적인 Assertion을 할 수 있습니다.
                .hasDroppedElements(); // Drop 된 데이터가 있음을 Assertion 합니다.
    }

    public static class BackpressureTestExample {
        public static Flux<Integer> generateNumber() {
            return Flux.create(emitter -> {
                for (int i = 1; i <= 100; i++) {
                    emitter.next(i);
                }
                emitter.complete();
            }, FluxSink.OverflowStrategy.ERROR);
        }
    }
}
