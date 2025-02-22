package com.insuk.reactor.ch14.multicasting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Publish_2 {
    private static final Logger log = LoggerFactory.getLogger(Publish_2.class);
    private static ConnectableFlux<String> publisher;
    private static int checkedAudience;
    static {
        publisher = Flux.just("Concert part1", "Connert part2","Connert part3")
                .delayElements(Duration.ofMillis(300L))
                .publish();
    }

    public static void main(String[] args) throws InterruptedException {
        checkAudience();
        Thread.sleep(500L);
        publisher.subscribe(data -> log.info("# audience 1 is watching: {}", data));
        checkedAudience++;

        Thread.sleep(500L);
        publisher.subscribe(data -> log.info("# audience 2 is watching: {}", data));
        checkedAudience++;

        checkAudience();
        Thread.sleep(500L);
        publisher.subscribe(data -> log.info("# audience 3 is watching: {}", data));
        Thread.sleep(1000L);
    }

    public static void checkAudience() {
        if (checkedAudience >= 2) {
            publisher.connect();
        }
    }
}
