package com.insuk.reactor.ch12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * - Context는 Operator 체인의 아래에서 위로 전파된다.
 * - 동일한 키에 대한 값을 중복해서 저장하면 Operator 체인에서 가장 위쪽에 위치한 contextWrite()이 저장한 값으로 덮어쓴다.
 */
public class Context_5 {
    private static final Logger log = LoggerFactory.getLogger(Context_5.class);

    public static void main(String[] args) throws InterruptedException {
        String key1 = "company";
        String key2 = "name";

        Mono.deferContextual(ctx -> Mono.just(ctx.get(key1)))
                .publishOn(Schedulers.parallel())
                .contextWrite(context -> context.put(key2, "Bill"))
                .transformDeferredContextual((mono, ctx) ->
                        mono.map(data -> data + ", " + ctx.getOrDefault(key2, "Steve"))
                )
                .contextWrite(context -> context.put(key1, "Apple"))
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(100L);
    }
}
/*
23:11:55.751 [parallel-1] INFO com.insuk.reactor.ch12.Context_5 -- # onNext: Apple, Bill, Steve
 */
/**
 * 따라서 일반적으로 모든 Operator에서 Context에 저장된 데이터를 읽을 수 있도록 contextWrite()을 Operator 체인의 맨 마지막에 둡니다.
 */