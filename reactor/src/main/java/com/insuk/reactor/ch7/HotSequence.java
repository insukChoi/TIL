package com.insuk.reactor.ch7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class HotSequence {
    private static final Logger log = LoggerFactory.getLogger(HotSequence.class);

    public static void main(String[] args) throws InterruptedException {
        String[] singers = {"Singer A", "Singer B", "Singer C", "Singer D", "Singer E"};

        log.info("# Begin concert:");

        Flux<String> concertFlux = Flux.fromArray(singers)
                .delayElements(Duration.ofSeconds(1))
                .share();

        concertFlux.subscribe(
                singer -> log.info("$ Subscriber1 is wahching {}'s song", singer)
        );

        Thread.sleep(2500);

        concertFlux.subscribe(
                singer -> log.info("$ Subscriber2 is wahching {}'s song", singer)
        );

        Thread.sleep(3000);
    }
}
/*
16:35:23.521 [main] INFO com.insuk.reactor.ch7.HotSequence -- # Begin concert:
16:35:24.588 [parallel-1] INFO com.insuk.reactor.ch7.HotSequence -- $ Subscriber1 is wahching Singer A's song
16:35:25.595 [parallel-2] INFO com.insuk.reactor.ch7.HotSequence -- $ Subscriber1 is wahching Singer B's song
16:35:26.602 [parallel-3] INFO com.insuk.reactor.ch7.HotSequence -- $ Subscriber1 is wahching Singer C's song
16:35:26.602 [parallel-3] INFO com.insuk.reactor.ch7.HotSequence -- $ Subscriber2 is wahching Singer C's song
16:35:27.608 [parallel-4] INFO com.insuk.reactor.ch7.HotSequence -- $ Subscriber1 is wahching Singer D's song
16:35:27.608 [parallel-4] INFO com.insuk.reactor.ch7.HotSequence -- $ Subscriber2 is wahching Singer D's song
16:35:28.609 [parallel-5] INFO com.insuk.reactor.ch7.HotSequence -- $ Subscriber1 is wahching Singer E's song
16:35:28.609 [parallel-5] INFO com.insuk.reactor.ch7.HotSequence -- $ Subscriber2 is wahching Singer E's song
 */
// main 스레드와 parallel-1, parallel-2, parallel-3 같은 스레드가 실행된 이유는 delayElements() Operator 의 디폴트 스레드 스케줄러가 parallel 이기 때문.