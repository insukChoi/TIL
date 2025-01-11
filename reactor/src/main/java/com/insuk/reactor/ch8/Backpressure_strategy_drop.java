package com.insuk.reactor.ch8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class Backpressure_strategy_drop {
    private static final Logger log = LoggerFactory.getLogger(Backpressure_strategy_drop.class);

    public static void main(String[] args) throws InterruptedException {
        Flux.interval(Duration.ofMillis(1L))
                .onBackpressureDrop(dropped -> log.info("# dropped: {}", dropped)) // Drop 전략
                .publishOn(Schedulers.parallel())
                .subscribe(data -> {
                            try {
                                Thread.sleep(5L);
                            } catch (InterruptedException e) {
                            }
                            log.info("# onNext: {}", data);
                        },
                        error -> log.error("# onError"));

        Thread.sleep(2000L);
    }
}
/*
...
15:48:21.731 [parallel-1] INFO com.insuk.reactor.ch8.Backpressure_strategy_drop -- # onNext: 39
15:48:21.737 [parallel-1] INFO com.insuk.reactor.ch8.Backpressure_strategy_drop -- # onNext: 40
15:48:21.744 [parallel-1] INFO com.insuk.reactor.ch8.Backpressure_strategy_drop -- # onNext: 41
15:48:21.745 [parallel-2] INFO com.insuk.reactor.ch8.Backpressure_strategy_drop -- # dropped: 256 -> 버퍼에 가득차면 기다리는 순서부터 지운다
15:48:21.746 [parallel-2] INFO com.insuk.reactor.ch8.Backpressure_strategy_drop -- # dropped: 257
15:48:21.747 [parallel-2] INFO com.insuk.reactor.ch8.Backpressure_strategy_drop -- # dropped: 258
15:48:21.748 [parallel-2] INFO com.insuk.reactor.ch8.Backpressure_strategy_drop -- # dropped: 259
15:48:21.749 [parallel-2] INFO com.insuk.reactor.ch8.Backpressure_strategy_drop -- # dropped: 260
15:48:21.750 [parallel-2] INFO com.insuk.reactor.ch8.Backpressure_strategy_drop -- # dropped: 261
15:48:21.750 [parallel-1] INFO com.insuk.reactor.ch8.Backpressure_strategy_drop -- # onNext: 42
 */