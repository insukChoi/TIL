package com.insuk.reactor.ch14.multicasting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class AutoConnect {
    private static final Logger log = LoggerFactory.getLogger(AutoConnect.class);

    public static void main(String[] args) throws InterruptedException {
        Flux<String> publisher = Flux.just("Concert 1", "Concert 2", "Concert 3")
                .delayElements(Duration.ofMillis(300L))
                .publish()
                .autoConnect(2);

        Thread.sleep(500);
        publisher.subscribe(data -> log.info("# audience 1 is watching: {}", data));

        Thread.sleep(500L);
        publisher.subscribe(data -> log.info("# audience 2 is watching: {}", data));

        Thread.sleep(500L);
        publisher.subscribe(data -> log.info("# audience 3 is watching: {}", data));

        Thread.sleep(1000L);
    }
}
/*
14:03:55.432 [parallel-1] INFO com.insuk.reactor.ch14.multicasting.AutoConnect -- # audience 1 is watching: Concert 1
14:03:55.436 [parallel-1] INFO com.insuk.reactor.ch14.multicasting.AutoConnect -- # audience 2 is watching: Concert 1
14:03:55.742 [parallel-2] INFO com.insuk.reactor.ch14.multicasting.AutoConnect -- # audience 1 is watching: Concert 2
14:03:55.743 [parallel-2] INFO com.insuk.reactor.ch14.multicasting.AutoConnect -- # audience 2 is watching: Concert 2
14:03:55.743 [parallel-2] INFO com.insuk.reactor.ch14.multicasting.AutoConnect -- # audience 3 is watching: Concert 2
14:03:56.049 [parallel-3] INFO com.insuk.reactor.ch14.multicasting.AutoConnect -- # audience 1 is watching: Concert 3
14:03:56.049 [parallel-3] INFO com.insuk.reactor.ch14.multicasting.AutoConnect -- # audience 2 is watching: Concert 3
14:03:56.049 [parallel-3] INFO com.insuk.reactor.ch14.multicasting.AutoConnect -- # audience 3 is watching: Concert 3
 */