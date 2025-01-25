package com.insuk.reactor.testing.stepVerifier;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.scheduler.VirtualTimeScheduler;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.time.Duration;

public class StepVerifierGeneralExample03Test {
    @Test
    public void getCOVID19CountTest() {
        StepVerifier
                .withVirtualTime(() -> TimeBasedTestExample.getCOVID19Count(
                        Flux.interval(Duration.ofHours(1)).take(1)
                ))
                .expectSubscription()
                .then(() -> VirtualTimeScheduler.get().advanceTimeBy(Duration.ofHours(1)))
                .expectNextCount(11)
                .expectComplete()
                .verify();
    }

    @Test
    public void getVoteCountTest() {
        StepVerifier
                .withVirtualTime(() -> TimeBasedTestExample.getVoteCount(
                        Flux.interval(Duration.ofMinutes(1))
                ))
                .expectSubscription()
                // expectNoEvent() 의 파라미터로 시간을 지정하면 지정한 시간 동안 어떤 이벤트도 발생하지 않을 것이라고 기대하는 동시에 지정한 시간만큼 시간을 앞당긴다는 것입니다.
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNoEvent(Duration.ofMinutes(1))
                .expectNextCount(5)
                .expectComplete()
                .verify();
    }

    public static class TimeBasedTestExample {
        public static Flux<Tuple2<String, Integer>> getCOVID19Count(Flux<Long> source) {
            return source
                    .flatMap(notUse -> Flux.just(
                            Tuples.of("서울", 10),
                            Tuples.of("경기도", 5),
                            Tuples.of("강원도", 3),
                            Tuples.of("충청도", 6),
                            Tuples.of("경상도", 5),
                            Tuples.of("전라도", 8),
                            Tuples.of("인천", 2),
                            Tuples.of("대전", 1),
                            Tuples.of("대구", 2),
                            Tuples.of("부산", 3),
                            Tuples.of("제주도", 0)
                    ));
        }

        public static Flux<Tuple2<String, Integer>> getVoteCount(Flux<Long> source) {
            return source.zipWith(Flux.just(
                    Tuples.of("중구", 15400),
                    Tuples.of("서초구", 20020),
                    Tuples.of("강서구", 32040),
                    Tuples.of("강동구", 14506),
                    Tuples.of("서대문구", 35650)
            )).map(Tuple2::getT2);
        }
    }
}
