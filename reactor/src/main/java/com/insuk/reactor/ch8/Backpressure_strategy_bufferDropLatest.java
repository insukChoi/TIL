package com.insuk.reactor.ch8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class Backpressure_strategy_bufferDropLatest {
    private static final Logger log = LoggerFactory.getLogger(Backpressure_strategy_bufferDropLatest.class);

    public static void main(String[] args) throws InterruptedException {
        Flux.interval(Duration.ofMillis(300L))
                .doOnNext(data -> log.info("# emitted by original Flux: {}", data))
                .onBackpressureBuffer(
                        2,
                        dropped -> log.info("# Overflow & Dropped: {} **", dropped),
                        BufferOverflowStrategy.DROP_LATEST
                )
                .doOnNext(data -> log.info("[# emitted by Buffer: {}]", data))
                .publishOn(Schedulers.parallel(), false, 1)
                .subscribe(data -> {
                            try {
                                Thread.sleep(1000L);
                            } catch (InterruptedException e) {
                            }
                            log.info("# onNext: {}", data);
                        },
                        error -> log.error("# onError"));

        Thread.sleep(3000L);
    }
}
/*
16:12:26.442 [parallel-2] INFO com.insuk.reactor.ch8.Backpressure_strategy_bufferDropLatest -- # emitted by original Flux: 0
16:12:26.445 [parallel-2] INFO com.insuk.reactor.ch8.Backpressure_strategy_bufferDropLatest -- [# emitted by Buffer: 0]
16:12:26.741 [parallel-2] INFO com.insuk.reactor.ch8.Backpressure_strategy_bufferDropLatest -- # emitted by original Flux: 1
16:12:27.039 [parallel-2] INFO com.insuk.reactor.ch8.Backpressure_strategy_bufferDropLatest -- # emitted by original Flux: 2
16:12:27.349 [parallel-2] INFO com.insuk.reactor.ch8.Backpressure_strategy_bufferDropLatest -- # emitted by original Flux: 3
16:12:27.350 [parallel-2] INFO com.insuk.reactor.ch8.Backpressure_strategy_bufferDropLatest -- # Overflow & Dropped: 3 **
16:12:27.448 [parallel-1] INFO com.insuk.reactor.ch8.Backpressure_strategy_bufferDropLatest -- # onNext: 0
...
 */