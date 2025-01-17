package com.insuk.reactor.ch12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * - Inner Sequence 내부에서는 외부 Context에 저장된 데이터를 읽을 수 있다.
 * - Inner Sequence 외부에서는 Inner Sequence 내부 Context에 저장된 데이터를 읽을 수 없다.
 */
public class Context_6 {
    private static final Logger log = LoggerFactory.getLogger(Context_6.class);

    public static void main(String[] args) throws InterruptedException {
        String key1 = "company";

        Mono.just("Steve")
//                .transformDeferredContextual((stringMono, ctx) -> ctx.get("role"))
                .flatMap(name ->
                        Mono.deferContextual(ctx ->
                                Mono.just(ctx.get(key1) + ", " + name)
                                        .transformDeferredContextual((mono, innerCtx) ->
                                                mono.map(data -> data + ", " + innerCtx.get("role"))
                                        )
                                        .contextWrite(context -> context.put("role", "CEO"))
                        )
                )
                .publishOn(Schedulers.parallel())
                .contextWrite(context -> context.put(key1, "Apple"))
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(100L);
    }
}
/*
23:28:54.392 [parallel-1] INFO com.insuk.reactor.ch12.Context_6 -- # onNext: Apple, Steve, CEO
 */
/**
 * 이 flatMap() Operator 내부에 있는 Sequence를 Inner Sequence라고 하는데,
 * Inner Sequence에서는 Inner Sequence 바깥쪽 Sequence에 연결된 Context의 값을 읽을 수 있습니다.
 * 실행 결과를 보면 바깥쪽 Sequence에 연결된 Context에 쓴 값인 'Apple'을 Inner Sequence에서 읽을 수 있다는 사실을 알 수 있습니다.
 *
 * 그런데 만약 19라인의 주석을 해제하고 코드를 다시 실행하면 아래와 같이 Context에 'role'이라는 키가 없어서 오류가 발생하는 것을 볼 수 있습니다.
 * Caused by: java.util.NoSuchElementException: Context does not contain key: role
 */