package com.insuk.reactor.ch13;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/**
 * checkpoint 를 사용하는 방법
 * : 디버그 모드를 활성화하는 방법이 애플리케이션 내에 있는 모든 Opearator에서 스택트레이스를 캡처하는 반면에, checkpoint() Opeator를 사용하면
 * 특정 Operator 체인 내의 스택트레이스만 캡쳐합니다.
 */
public class Debug_2 {
    private static final Logger log = LoggerFactory.getLogger(Debug_2.class);

    public static void main(String[] args) {
        Flux.just(2, 4, 6, 8)
                .zipWith(Flux.just(1, 2, 3, 0), (x, y) -> x / y)
                .checkpoint("zipWith.checkpoint")
                .map(num -> num + 2)
                .checkpoint("map.checkpoint")
                .subscribe(
                        data -> log.info("# onNext: {}", data),
                        error -> log.error("# onError:", error)
                );
    }
}
/*
Error has been observed at the following site(s):
	*__checkpoint ⇢ zipWith.checkpoint               => 에러의 직접적인 원인
	|_ checkpoint ⇢ map.checkpoint                   => 에러가 전파되어 출력됨
 */
