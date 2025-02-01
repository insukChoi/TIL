package com.insuk.reactor.ch14.transfer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * zip() Operator는 파라미터로 입력되는 Publisher Sequence에서 emit된 데이터를 결합하는데,
 * 각 Publisher가 데이터를 하나씩 emit할 때까지 기다렸다가 결합합니다.
 */
public class Zip_1 {
    private static final Logger log = LoggerFactory.getLogger(Zip_1.class);

    public static void main(String[] args) throws InterruptedException {
        Flux.zip(
                Flux.just(1, 2, 3).delayElements(Duration.ofMillis(300L)),
                Flux.just(4, 5, 6).delayElements(Duration.ofMillis(500L))
        ).subscribe(tuple2 -> log.info("# onNext: {}", tuple2));

        Thread.sleep(2500);
    }
}
/*
17:29:30.856 [parallel-2] INFO com.insuk.reactor.ch14.transfer.Zip_1 -- # onNext: [1,4]
17:29:31.366 [parallel-4] INFO com.insuk.reactor.ch14.transfer.Zip_1 -- # onNext: [2,5]
17:29:31.872 [parallel-6] INFO com.insuk.reactor.ch14.transfer.Zip_1 -- # onNext: [3,6]
 */