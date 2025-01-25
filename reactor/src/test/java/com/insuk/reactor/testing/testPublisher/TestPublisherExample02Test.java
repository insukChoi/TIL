package com.insuk.reactor.testing.testPublisher;

import com.insuk.reactor.testing.stepVerifier.StepVerifierGeneralExample02Test;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

import java.util.Arrays;
import java.util.List;

public class TestPublisherExample02Test {
    @Test
    public void divideByTwoTest() {
        TestPublisher<Integer> source = TestPublisher.createNoncompliant(TestPublisher.Violation.ALLOW_NULL); // null 이어도 정상 동작하는 TestPublisher 를 생성합니다.

        StepVerifier.create(StepVerifierGeneralExample02Test.GeneralTestExample.divideByTwo(source.flux()))
                .expectSubscription()
                .then(() -> {
                    getDataSource().stream()
                            .forEach(data -> source.next(data));
                    source.complete();
                })
                .expectNext(1, 2, 3, 4, 5)
                .expectComplete()
                .verify();
    }

    private static List<Integer> getDataSource() {
        return Arrays.asList(2, 4, 6, 8, null);
    }
}
