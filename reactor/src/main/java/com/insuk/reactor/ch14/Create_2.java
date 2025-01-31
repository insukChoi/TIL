package com.insuk.reactor.ch14;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

public class Create_2 {
    private static final Logger log = LoggerFactory.getLogger(Create_2.class);
    static int start = 1;
    static int end = 4;

    public static void main(String[] args) throws InterruptedException {
        Flux.create(fluxSink -> {
            fluxSink.onRequest(n -> {
                log.info("# requested: " + n);
                try {
                    Thread.sleep(500L);
                    for (int i = start; i < end; i++) {
                        fluxSink.next(i);
                    }
                    start += 4;
                    end += 4;
                } catch (InterruptedException e) {}
            });

            fluxSink.onDispose(() -> log.info("# clean up"));
        }, FluxSink.OverflowStrategy.DROP)
                .subscribeOn(Schedulers.boundedElastic())
                .publishOn(Schedulers.parallel(), 2)
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(3000L);
    }
}
