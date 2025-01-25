package com.insuk.reactor.ch13;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.HashMap;
import java.util.Map;

/**
 * Hooks.onOperatorDebug() 사용의 한계: 디버그 모드의 활성화는 다음과 같이 애플리케이션 내에서 비용이 많이 드는 동작 과정을 거칩니다.
 * - 애플리케이션 내에 있는 모든 Operator의 스택트레이스를 캡쳐한다.
 * - 에러가 발생하면 캡처한 정보를 기반으로 에러가 발생한 Assembly의 스택트레이스를 원본 스택트레이스 중간에 끼워 넣는다.
 *
 * 따라서, 처음부터 디버스 모드를 활성화하는 것은 권장하지 않습니다.
 */
public class Debug_1 {
    private static final Logger log = LoggerFactory.getLogger(Debug_1.class);
    public static Map<String, String> fruits = new HashMap<>();

    static {
        fruits.put("banana", "바나나");
        fruits.put("apple", "사과");
        fruits.put("pear", "배");
        fruits.put("grape", "포도");
    }

    public static void main(String[] args) throws InterruptedException {
//        Hooks.onOperatorDebug();
        Flux.fromArray(new String[]{"BANANAS", "APPLES", "PEARS", "MELONS"})
                .subscribeOn(Schedulers.boundedElastic())
                .publishOn(Schedulers.parallel())
                .map(String::toLowerCase)
                .map(fruit -> fruit.substring(0, fruit.length() - 1))
                .map(fruits::get)
                .map(translated -> "맛있는 " + translated)
                .subscribe(
                        log::info,
                        error -> log.error("# onError:", error)
                );

        Thread.sleep(100L);
    }
}
