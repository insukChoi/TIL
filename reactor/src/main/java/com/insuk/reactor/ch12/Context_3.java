package com.insuk.reactor.ch12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class Context_3 {
    private static final Logger log = LoggerFactory.getLogger(Context_3.class);

    public static void main(String[] args) throws InterruptedException {
        final String key1 = "company";
        final String key2 = "firstName";
        final String key3 = "lastName";

        Mono.deferContextual(ctx ->
                Mono.just(ctx.get(key1) + ", " +
                        ctx.getOrEmpty(key2).orElse("no firstName") + " " +
                        ctx.getOrDefault(key3, "no lastname"))
                )
                .publishOn(Schedulers.parallel())
                .contextWrite(context -> context.put(key1, "Apple"))
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(100L);
    }
}
/*
22:54:29.889 [parallel-1] INFO com.insuk.reactor.ch12.Context_3 -- # onNext: Apple, no firstName no lastname
 */