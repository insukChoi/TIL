package com.insuk.reactor.testing.publisherProbe;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.publisher.PublisherProbe;

public class PublisherProbeExample01Test {
    @Test
    public void publisherProbeTest() {
        PublisherProbe<String> probe = PublisherProbe.of(PublisherProbeExample.applyStandbyPower());

        StepVerifier.create(
                        PublisherProbeExample.processTask(
                                PublisherProbeExample.supplyMainPower(),
                                probe.mono()
                        )
                )
                .expectNextCount(1)
                .verifyComplete();

        probe.assertWasSubscribed();
        probe.assertWasRequested();
        probe.assertWasNotCancelled();
    }

    public static class PublisherProbeExample {
        public static Mono<String> processTask(Mono<String> main, Mono<String> standby) {
            return main.flatMap(massage -> Mono.just(massage))
                    .switchIfEmpty(standby); // Upstream Publisher 가 데이터 emit 없이 종료되는 경우, 대체 Publisher가 데이터를 emit 합니다.
        }

        public static Mono<String> supplyMainPower() {
            return Mono.empty();
        }

        public static Mono<String> applyStandbyPower() {
            return Mono.just("# supply Standby Power");
        }
    }
}
