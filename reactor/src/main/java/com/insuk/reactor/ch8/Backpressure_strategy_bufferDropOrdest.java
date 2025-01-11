package com.insuk.reactor.ch8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.BufferOverflowStrategy;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class Backpressure_strategy_bufferDropOrdest {
    private static final Logger log = LoggerFactory.getLogger(Backpressure_strategy_bufferDropOrdest.class);

    public static void main(String[] args) throws InterruptedException {
        Flux.interval(Duration.ofMillis(300L))
                .doOnNext(data -> log.info("# emitted by original Flux: {}", data))
                .onBackpressureBuffer(
                        2,
                        dropped -> log.info("# Overflow & Dropped: {} **", dropped),
                        BufferOverflowStrategy.DROP_OLDEST
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
16:25:53.310 [parallel-2] INFO com.insuk.reactor.ch8.Backpressure_strategy_bufferDropOrdest -- # emitted by original Flux: 0
16:25:53.313 [parallel-2] INFO com.insuk.reactor.ch8.Backpressure_strategy_bufferDropOrdest -- [# emitted by Buffer: 0]
16:25:53.609 [parallel-2] INFO com.insuk.reactor.ch8.Backpressure_strategy_bufferDropOrdest -- # emitted by original Flux: 1
16:25:53.907 [parallel-2] INFO com.insuk.reactor.ch8.Backpressure_strategy_bufferDropOrdest -- # emitted by original Flux: 2
16:25:54.208 [parallel-2] INFO com.insuk.reactor.ch8.Backpressure_strategy_bufferDropOrdest -- # emitted by original Flux: 3
16:25:54.211 [parallel-2] INFO com.insuk.reactor.ch8.Backpressure_strategy_bufferDropOrdest -- # Overflow & Dropped: 1 **
16:25:54.316 [parallel-1] INFO com.insuk.reactor.ch8.Backpressure_strategy_bufferDropOrdest -- # onNext: 0
16:25:54.317 [parallel-1] INFO com.insuk.reactor.ch8.Backpressure_strategy_bufferDropOrdest -- [# emitted by Buffer: 2]
16:25:54.508 [parallel-2] INFO com.insuk.reactor.ch8.Backpressure_strategy_bufferDropOrdest -- # emitted by original Flux: 4
16:25:54.805 [parallel-2] INFO com.insuk.reactor.ch8.Backpressure_strategy_bufferDropOrdest -- # emitted by original Flux: 5
16:25:54.805 [parallel-2] INFO com.insuk.reactor.ch8.Backpressure_strategy_bufferDropOrdest -- # Overflow & Dropped: 3 **
16:25:55.108 [parallel-2] INFO com.insuk.reactor.ch8.Backpressure_strategy_bufferDropOrdest -- # emitted by original Flux: 6
16:25:55.109 [parallel-2] INFO com.insuk.reactor.ch8.Backpressure_strategy_bufferDropOrdest -- # Overflow & Dropped: 4 **
16:25:55.321 [parallel-1] INFO com.insuk.reactor.ch8.Backpressure_strategy_bufferDropOrdest -- # onNext: 2
...
 */