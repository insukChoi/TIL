package com.insuk.reactor.ch14;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

/**
 * justOrEmpty()는 just()의 확장 Operator로서, just() Operator와 달리, emit할 데이터가 null일 경우 NullPointException이 발생하지 않고
 * OnComplete Signal을 전송합니다.
 */
public class JustOrEmpty {

    private static final Logger log = LoggerFactory.getLogger(JustOrEmpty.class);

    public static void main(String[] args) {
        Mono.justOrEmpty(null)
                .subscribe(
                        data -> {},
                        error -> {},
                        () -> log.info("# onComplete")
                );
    }
}
/*
14:58:01.404 [main] INFO com.insuk.reactor.ch14.JustOrEmpty -- # onComplete
 */
