package com.insuk.reactor.ch12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

/**
 * Context API
 * - put(key, value) : key/value 형태로 Context에 값을 쓴다.
 * - of(key1,value1,key2,value2,...) : key/value 형태로 Context에 여러 개의 값을 쓴다. (5개까지 가능)
 * - putAll(ContextView) : 현재 Context와 파라미터로 입력된 ContextView를 merge한다.
 * - delete(key) : Context에서 key에 해당하는 value를 삭제한다.
 */
public class Context_2 {
    private static final Logger log = LoggerFactory.getLogger(Context_2.class);

    public static void main(String[] args) throws InterruptedException {
        final String key1 = "company";
        final String key2 = "firstName";
        final String key3 = "secondName";

        Mono.deferContextual(ctx ->
                Mono.just(ctx.get(key1) + ", " + ctx.get(key2) + ", " + ctx.get(key3))
        )
                .publishOn(Schedulers.parallel())
                .contextWrite(context ->
                        context.putAll(Context.of(key2, "Steve", key3, "Jobs").readOnly())
                )
                .contextWrite(context -> context.put(key1, "Apple"))
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(100L);
    }
}
/*
22:54:45.998 [parallel-1] INFO com.insuk.reactor.ch12.Context_2 -- # onNext: Apple, Steve, Jobs
 */