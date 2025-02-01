package com.insuk.reactor.ch14.transfer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Zip_2 {
    private static final Logger log = LoggerFactory.getLogger(Zip_2.class);

    public static void main(String[] args) throws InterruptedException {
        Flux.zip(
                Flux.just(1, 2, 3).delayElements(Duration.ofMillis(300L)),
                Flux.just(4, 5, 6).delayElements(Duration.ofMillis(500L)),
                (n1, n2) -> n1 * n2
        ).subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(2500);
    }
}
/*
17:40:22.057 [parallel-2] INFO com.insuk.reactor.ch14.transfer.Zip_2 -- # onNext: 4
17:40:22.559 [parallel-4] INFO com.insuk.reactor.ch14.transfer.Zip_2 -- # onNext: 10
17:40:23.062 [parallel-6] INFO com.insuk.reactor.ch14.transfer.Zip_2 -- # onNext: 18
 */