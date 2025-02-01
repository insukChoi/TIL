package com.insuk.reactor.ch14.transfer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * and() Operator는 Mono의 Complete Signal과 파라미터로 입력된 Publisher의 Complete Signal을 결합하여
 * 새로운 Mono<Void>를 반환합니다.
 */
public class And {
    private static final Logger log = LoggerFactory.getLogger(And.class);

    public static void main(String[] args) throws InterruptedException {
        Mono.just("Task 1")
                .delayElement(Duration.ofSeconds(1))
                .doOnNext(data -> log.info("# Mono doOnNext: {}", data))
                .and(
                        Flux.just("Task 2", "Task 3")
                                .delayElements(Duration.ofMillis(600))
                                .doOnNext(data -> log.info("# Flux doOnNext: {}", data))
                ).subscribe(
                        data -> log.info("# onNext: {}" , data),
                        error -> log.error("# onError: {} ", error),
                        () -> log.info("# onComplete")
                );

        Thread.sleep(5000);
    }
}
/*
18:22:09.066 [parallel-2] INFO com.insuk.reactor.ch14.transfer.And -- # Flux doOnNext: Task 2
18:22:09.464 [parallel-1] INFO com.insuk.reactor.ch14.transfer.And -- # Mono doOnNext: Task 1
18:22:09.675 [parallel-3] INFO com.insuk.reactor.ch14.transfer.And -- # Flux doOnNext: Task 3
18:22:09.676 [parallel-3] INFO com.insuk.reactor.ch14.transfer.And -- # onComplete
 */
/**
 * 결과적으로 Subscriber에게 onComplete Signal만 전달되고, Upstream에서 emit된 데이터는 전달되지 않습니다.
 * 즉, and() Operator는 모든 Sequence가 종료되길 기다렸다가 최종적으로 onComplete Signal만 전송합니다.
 */