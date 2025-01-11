package com.insuk.reactor.ch8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class Backpressure_strategy_error {
    private static final Logger log = LoggerFactory.getLogger(Backpressure_strategy_error.class);

    public static void main(String[] args) throws InterruptedException {
        Flux.interval(Duration.ofMillis(1L))
                .onBackpressureError() // Error 전략 (버퍼가 다 차면 onError 시그널을 보냄)
                .doOnNext(data -> log.info("# doOnNext: {}", data))
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
