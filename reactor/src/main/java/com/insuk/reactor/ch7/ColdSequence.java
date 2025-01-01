package com.insuk.reactor.ch7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.Arrays;

public class ColdSequence {
    private static final Logger log = LoggerFactory.getLogger(ColdSequence.class);

    public static void main(String[] args) throws InterruptedException {
        Flux<String> coldFlux = Flux
                .fromIterable(Arrays.asList("KOREA", "JAPAN", "CHINES"))
                .map(String::toLowerCase);


        coldFlux.subscribe(country -> log.info("# Subscriber1: {}", country));
        System.out.println("--------------------");
        Thread.sleep(2000L);
        coldFlux.subscribe(country -> log.info("# Subscriber2: {}", country));
    }
}
/*
 * 16:30:31.272 [main] INFO com.insuk.reactor.ch7.ColdSequence -- # Subscriber1: korea
 * 16:30:31.274 [main] INFO com.insuk.reactor.ch7.ColdSequence -- # Subscriber1: japan
 * 16:30:31.274 [main] INFO com.insuk.reactor.ch7.ColdSequence -- # Subscriber1: chines
 * --------------------
 * 16:30:33.281 [main] INFO com.insuk.reactor.ch7.ColdSequence -- # Subscriber2: korea
 * 16:30:33.282 [main] INFO com.insuk.reactor.ch7.ColdSequence -- # Subscriber2: japan
 * 16:30:33.282 [main] INFO com.insuk.reactor.ch7.ColdSequence -- # Subscriber2: chines
 */