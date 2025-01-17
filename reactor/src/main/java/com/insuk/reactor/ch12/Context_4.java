package com.insuk.reactor.ch12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class Context_4 {
    private static final Logger log = LoggerFactory.getLogger(Context_4.class);

    public static void main(String[] args) throws InterruptedException {
        final String key1 = "company";

        Mono<String> mono = Mono.deferContextual(ctx ->
                Mono.just("Company: " + " " + ctx.get(key1))
        ).publishOn(Schedulers.parallel());

        mono.contextWrite(context -> context.put(key1, "Apple"))
                .subscribe(data -> log.info("# subscribe1 onNext {}", data));

        mono.contextWrite(context -> context.put(key1, "Microsoft"))
                .subscribe(data -> log.info("# subscribe2 onNext {}", data));

        Thread.sleep(100L);
    }
}
/*
23:05:06.656 [parallel-1] INFO com.insuk.reactor.ch12.Context_4 -- # subscribe1 onNext Company:  Apple
23:05:06.656 [parallel-2] INFO com.insuk.reactor.ch12.Context_4 -- # subscribe2 onNext Company:  Microsoft
 */
/**
 * 구독이 발생할 때마다 해당하는 하나의 Context가 하나의 구독에 연결됩니다.
 */