package com.insuk.reactor.ch14.creating;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * defer() Operator는 Operator를 선언한 시점에 데이터를 emit하는 것이 아니라
 * 구독하는 시점에 데이터를 emit하는 Flux 또는 Mono를 생성한다.
 */
public class Differ {
    private static final Logger log = LoggerFactory.getLogger(Differ.class);

    public static void main(String[] args) throws InterruptedException {
        log.info("# start: {}", LocalDateTime.now());
        Mono<LocalDateTime> justMono = Mono.just(LocalDateTime.now());
        Mono<LocalDateTime> deferMono = Mono.defer(() -> Mono.just(LocalDateTime.now()));

        Thread.sleep(2000);

        justMono.subscribe(data -> log.info("# onNext just1: {}", data));
        deferMono.subscribe(data -> log.info("# onNext defer1: {}", data));

        Thread.sleep(2000);

        justMono.subscribe(data -> log.info("# onNext just2: {}", data));
        deferMono.subscribe(data -> log.info("# onNext defer2: {}", data));
    }
}
/*
16:15:27.153 [main] INFO com.insuk.reactor.ch14.creating.Differ -- # start: 2025-01-30T16:15:27.153358
16:15:29.188 [main] INFO com.insuk.reactor.ch14.creating.Differ -- # onNext just1: 2025-01-30T16:15:27.155671
16:15:29.189 [main] INFO com.insuk.reactor.ch14.creating.Differ -- # onNext defer1: 2025-01-30T16:15:29.189053
16:15:31.196 [main] INFO com.insuk.reactor.ch14.creating.Differ -- # onNext just2: 2025-01-30T16:15:27.155671
16:15:31.198 [main] INFO com.insuk.reactor.ch14.creating.Differ -- # onNext defer2: 2025-01-30T16:15:31.198176
 */
/**
 * just() Operator는 사실 Hot Publisher 이기 때문에 Subscriber의 구독 여부와는 상관없이 데이터를 emit 하게 됩니다.
 * 그리고 구독이 발생하면 emit된 데이터를 다시 재생(reply)해서 Subscriber에게 전달하는 것입니다.
 *
 * 그런데 defer() Operator는 구독이 발생하기 전까지 데이터의 emit을 지연시키기 때문이다.
 */