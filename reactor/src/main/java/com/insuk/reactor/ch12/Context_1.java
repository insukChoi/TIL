package com.insuk.reactor.ch12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Reactor 의 Context는 ThreadLocal과 다소 유사한 면이 있지만, 각각의 실행 스레드와 매핑되는 ThreadLocal과 달리
 * 스레드와 매핑되는 것이 아니라 Subscriber와 매핑됩니다.
 *
 * Context에 key/value 형태의 데이터를 저장할 수 있다는 의미는 Context에 데이터의 쓰기와 읽기가 가능하다는 의미이다.
 */
public class Context_1 {
    private static final Logger log = LoggerFactory.getLogger(Context_1.class);

    public static void main(String[] args) throws InterruptedException {
        Mono.deferContextual(
                ctx ->
                        Mono.just("Hello" + " " + ctx.get("firstName"))
                                .doOnNext(data -> log.info("# just doOnNext: {}", data))
                )
                .subscribeOn(Schedulers.boundedElastic())
                .publishOn(Schedulers.parallel())
                .transformDeferredContextual(
                        (mono, ctx) -> mono.map(data -> data + " " + ctx.get("lastName"))
                )
                .contextWrite(context -> context.put("lastName", "Jobs"))
                .contextWrite(context -> context.put("firstName", "Steve"))
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(100L);
    }
}
/*
22:14:46.348 [boundedElastic-1] INFO com.insuk.reactor.ch12.Context_1 -- # just doOnNext: Hello Steve
22:14:46.351 [parallel-1] INFO com.insuk.reactor.ch12.Context_1 -- # onNext: Hello Steve Jobs
 */
/**
 * Context에서 데이터를 읽어 오는 작업을 각각 다른 스레드에서 수행했음을 알 수 있습니다.
 * 이처럼, Reactor에서는 Operator 체인상의 서로 다른 스레드들이 Context의 저장된 데이터에 손쉽게 접근할 수 있습니다.
 */
