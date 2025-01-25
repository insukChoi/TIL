package com.insuk.reactor.ch13;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;

/**
 * log() Operator는 Reactor Sequence의 동작을 로그로 출력하는데, 이 로그를 통해 디버깅이 가능합니다.
 */
public class Debug_4 {
    private static final Logger log = LoggerFactory.getLogger(Debug_4.class);
    public static Map<String, String> fruits = new HashMap<>();

    static {
        fruits.put("banana", "바나나");
        fruits.put("apple", "사과");
        fruits.put("pear", "배");
        fruits.put("grape", "포도");
    }

    public static void main(String[] args) {
        Flux.fromArray(new String[] {"BANANAS", "APPLES", "PEARS", "MELONS"})
                .map(String::toLowerCase)
                .map(fruit -> fruit.substring(0, fruit.length() - 1))
                .log()
                .map(fruits::get)
                .subscribe(
                        log::info,
                        error -> log.error("# onError:", error)
                );
    }
}
/*
13:39:29.037 [main] INFO reactor.Flux.MapFuseable.1 -- | onSubscribe([Fuseable] FluxMapFuseable.MapFuseableSubscriber)
13:39:29.041 [main] INFO reactor.Flux.MapFuseable.1 -- | request(unbounded)
13:39:29.042 [main] INFO reactor.Flux.MapFuseable.1 -- | onNext(banana)
13:39:29.042 [main] INFO com.insuk.reactor.ch13.Debug_4 -- 바나나
13:39:29.042 [main] INFO reactor.Flux.MapFuseable.1 -- | onNext(apple)
13:39:29.042 [main] INFO com.insuk.reactor.ch13.Debug_4 -- 사과
13:39:29.042 [main] INFO reactor.Flux.MapFuseable.1 -- | onNext(pear)
13:39:29.042 [main] INFO com.insuk.reactor.ch13.Debug_4 -- 배
13:39:29.042 [main] INFO reactor.Flux.MapFuseable.1 -- | onNext(melon)
13:39:29.044 [main] INFO reactor.Flux.MapFuseable.1 -- | cancel()
 */
