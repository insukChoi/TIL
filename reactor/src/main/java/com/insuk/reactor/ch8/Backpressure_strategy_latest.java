package com.insuk.reactor.ch8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class Backpressure_strategy_latest {
    private static final Logger log = LoggerFactory.getLogger(Backpressure_strategy_latest.class);

    public static void main(String[] args) throws InterruptedException {
        Flux.interval(Duration.ofMillis(1L))
                .onBackpressureLatest()
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
..
15:55:02.907 [parallel-1] INFO com.insuk.reactor.ch8.Backpressure_strategy_latest -- # onNext: 253
15:55:02.914 [parallel-1] INFO com.insuk.reactor.ch8.Backpressure_strategy_latest -- # onNext: 254
15:55:02.920 [parallel-1] INFO com.insuk.reactor.ch8.Backpressure_strategy_latest -- # onNext: 255
15:55:02.927 [parallel-1] INFO com.insuk.reactor.ch8.Backpressure_strategy_latest -- # onNext: 1208 -> 버퍼가 가득차면 가장 최근데이터 이전데이터는 다 지운다
15:55:02.933 [parallel-1] INFO com.insuk.reactor.ch8.Backpressure_strategy_latest -- # onNext: 1209
15:55:02.939 [parallel-1] INFO com.insuk.reactor.ch8.Backpressure_strategy_latest -- # onNext: 1210
 */
