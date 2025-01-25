package com.insuk.reactor.testing.testPublisher;

import com.insuk.reactor.testing.stepVerifier.StepVerifierGeneralExample02Test;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

/**
 * TestPublisher를 사용하면, 예제 코드처럼 간단한 숫자를 테스트하는 것이 아니라 복잡한 로직이 포함된 대상 메서드를 테스트하거나 조건에 따라서
 * signal을 변경해야 되는 등의 특정 상황을 테스트하기가 용이할 것입니다.
 */
public class TestPublisherExample01Test {
    @Test
    public void dividedByTwoTest() {
        TestPublisher<Integer> source = TestPublisher.create();

        StepVerifier.create(StepVerifierGeneralExample02Test.GeneralTestExample.divideByTwo(source.flux()))
                .expectSubscription()
                .then(() -> source.emit(2, 4, 6, 8, 10))
                .expectNext(1, 2, 3, 4)
                .expectError()
                .verify();
    }
}
