package com.insuk.reactor.ch8;

import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class Backpressure_1 {
    private static final Logger log = LoggerFactory.getLogger(Backpressure_1.class);

    public static void main(String[] args) {
        Flux.range(1, 5)
                .doOnRequest(data -> log.info("# doOnRequest: {}", data))
                .subscribe(new BaseSubscriber<>() {
                    @Override
                    protected void hookOnSubscribe(final Subscription subscription) {
                        request(1);
                    }

                    @Override
                    protected void hookOnNext(final Integer value) {
                        try {
                            Thread.sleep(2000L);
                            log.info("# hookOnNext: {}", value);
                            request(1);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }
}
/*
15:01:17.352 [main] INFO com.insuk.reactor.ch8.Backpressure_1 -- # doOnRequest: 1
15:01:19.359 [main] INFO com.insuk.reactor.ch8.Backpressure_1 -- # hookOnNext: 1
15:01:19.360 [main] INFO com.insuk.reactor.ch8.Backpressure_1 -- # doOnRequest: 1
15:01:21.365 [main] INFO com.insuk.reactor.ch8.Backpressure_1 -- # hookOnNext: 2
15:01:21.365 [main] INFO com.insuk.reactor.ch8.Backpressure_1 -- # doOnRequest: 1
15:01:23.370 [main] INFO com.insuk.reactor.ch8.Backpressure_1 -- # hookOnNext: 3
15:01:23.372 [main] INFO com.insuk.reactor.ch8.Backpressure_1 -- # doOnRequest: 1
15:01:25.376 [main] INFO com.insuk.reactor.ch8.Backpressure_1 -- # hookOnNext: 4
15:01:25.379 [main] INFO com.insuk.reactor.ch8.Backpressure_1 -- # doOnRequest: 1
15:01:27.385 [main] INFO com.insuk.reactor.ch8.Backpressure_1 -- # hookOnNext: 5
15:01:27.386 [main] INFO com.insuk.reactor.ch8.Backpressure_1 -- # doOnRequest: 1
 */
