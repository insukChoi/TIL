package com.insuk.reactor.ch14.transfer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * merge() Operator는 파라미터로 입력되는 Publisher의 Sequence에서 emit된 데이터를 인터리빙(Interleave) 방식으로 병합합니다.
 *
 * merge() Operator는 concat() Operator처럼 먼저 입력된 Publisher의 Sequence가 종료될 때까지 나머지 Publisher의 Sequence가 subscribe되지 않고 대기하는 것이 아니라
 * 모든 Publisher의 Sequence가 즉시 subscribe 됩니다.
 */
public class Merge {
    private static final Logger log = LoggerFactory.getLogger(Merge.class);

    public static void main(String[] args) throws InterruptedException {
        Flux.merge(
                Flux.just(1, 2, 3, 4).delayElements(Duration.ofMillis(300L)),
                Flux.just(5, 6, 7).delayElements(Duration.ofMillis(500L))
        ).subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(2000L);
    }
}
/*
17:24:06.192 [parallel-1] INFO com.insuk.reactor.ch14.transfer.Merge -- # onNext: 1
17:24:06.390 [parallel-2] INFO com.insuk.reactor.ch14.transfer.Merge -- # onNext: 5
17:24:06.500 [parallel-3] INFO com.insuk.reactor.ch14.transfer.Merge -- # onNext: 2
17:24:06.806 [parallel-5] INFO com.insuk.reactor.ch14.transfer.Merge -- # onNext: 3
17:24:06.895 [parallel-4] INFO com.insuk.reactor.ch14.transfer.Merge -- # onNext: 6
17:24:07.107 [parallel-6] INFO com.insuk.reactor.ch14.transfer.Merge -- # onNext: 4
17:24:07.401 [parallel-7] INFO com.insuk.reactor.ch14.transfer.Merge -- # onNext: 7
 */