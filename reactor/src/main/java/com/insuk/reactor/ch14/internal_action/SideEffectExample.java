package com.insuk.reactor.ch14.internal_action;

import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

/**
 * Reactor에서는 Upstream Publisher에서 emit되는 데이터의 변경 없이 부수효과(Side-Effect)만을 수행하기 위한 Operator들이 있는데,
 * doOnXXXX()으로 시작하는 Operator가 바로 그러한 역할을 하는 Operator입니다.
 *
 * doOnXXXX()으로 시작하는 Operator는 Consumer 또는 Runnable 타입의 함수형 인터페이스를 파라미터로 가지기 때문에 별도의 리턴 값이 없습니다.
 *
 * - doOnSubscribe() : Publisher가 구독 중일 때 트리거되는 동작을 추가할 수 있다.
 * - doOnRequest() : Publisher가 요청을 수신할 때 트리거되는 동작을 추가할 수 있다.
 * - doOnNext() : Publisher가 데이터를 emit할 때 트리거되는 동작을 추가할 수 있다.
 * - doOnComplete() : Publisher가 성공적으로 완료되었을 때 트리거되는 동작을 추가할 수 있다.
 * - doOnError() : Publisher가 에러가 발생한 상태로 종료되었을 때 트리거되는 동작을 추가할 수 있다.
 * - doOnCancel() : Publisher가 취소되었을 때 트리거되는 동작을 추가할 수 있다.
 * - doOnTerminate() : Publisher가 성공적으로 완료되었을 때 또는 에러가 발생한 상태로 종료되었을 때 트러거 되는 동작을 추가할 수 있다.
 * - doOnEach() : Publisher가 데이터를 emit할 때, 성공적으로 완료되었을 때, 에러가 발생한 상태로 종료되었을 때 트러거되는 동작을 추가할 수 있다.
 * - doOnDiscard() : Upstream에 있는 전체 Operator 체인의 동작 중에서 Operator에 의해 폐기되는 요소를 조건부로 정리할 수 있다.
 * - doOnAfterTerminate() : Downstream을 성공적으로 완료한 직후 또는 에러가 발생하여 Publisher가 종료된 직후에 트리거되는 동작을 추가할 수 있다.
 * - doFirst() : Publisher가 구독되기 전에 트리거되는 동작을 추가할 수 있다.
 * - doFinally() : 에러를 포함해서 어떤 이유이든 간에 Publisher가 종료된 후 트리거되는 동작을 추가할 수 있다.
 */
public class SideEffectExample {
    private static final Logger log = LoggerFactory.getLogger(SideEffectExample.class);

    public static void main(String[] args) {
        Flux.range(1, 5)
                .doFinally(signalType -> log.info("# doFinally 1: {}", signalType))
                .doFinally(signalType -> log.info("# doFinally 2: {}", signalType))
                .doOnNext(data -> log.info("# range > doOnNext: {}", data))
                .doOnRequest(data -> log.info("# doOnRequest: {}", data))
                .doOnSubscribe(subscription -> log.info("# doOnSubscribe 1"))
                .doFirst(() -> log.info("# doFirst()"))
                .filter(num -> num % 2 == 1)
                .doOnNext(data -> log.info("# filter > doOnNext: {}", data))
                .doOnComplete(() -> log.info("# doOnComplete()"))
                .subscribe(new BaseSubscriber<>() {
                    @Override
                    protected void hookOnSubscribe(final Subscription subscription) {
                        request(1);
                    }

                    @Override
                    protected void hookOnNext(final Integer value) {
                        log.info("# hookOnNext: {}", value);
                        request(1);
                    }
                });
    }
}
/*
20:20:35.955 [main] INFO com.insuk.reactor.ch14.internal_action.SideEffectExample -- # doFirst()
20:20:35.959 [main] INFO com.insuk.reactor.ch14.internal_action.SideEffectExample -- # doOnSubscribe 1
20:20:35.959 [main] INFO com.insuk.reactor.ch14.internal_action.SideEffectExample -- # doOnRequest: 1
20:20:35.959 [main] INFO com.insuk.reactor.ch14.internal_action.SideEffectExample -- # range > doOnNext: 1
20:20:35.959 [main] INFO com.insuk.reactor.ch14.internal_action.SideEffectExample -- # filter > doOnNext: 1
20:20:35.959 [main] INFO com.insuk.reactor.ch14.internal_action.SideEffectExample -- # hookOnNext: 1
20:20:35.959 [main] INFO com.insuk.reactor.ch14.internal_action.SideEffectExample -- # doOnRequest: 1
20:20:35.959 [main] INFO com.insuk.reactor.ch14.internal_action.SideEffectExample -- # range > doOnNext: 2
20:20:35.959 [main] INFO com.insuk.reactor.ch14.internal_action.SideEffectExample -- # range > doOnNext: 3
20:20:35.959 [main] INFO com.insuk.reactor.ch14.internal_action.SideEffectExample -- # filter > doOnNext: 3
20:20:35.959 [main] INFO com.insuk.reactor.ch14.internal_action.SideEffectExample -- # hookOnNext: 3
20:20:35.959 [main] INFO com.insuk.reactor.ch14.internal_action.SideEffectExample -- # doOnRequest: 1
20:20:35.959 [main] INFO com.insuk.reactor.ch14.internal_action.SideEffectExample -- # range > doOnNext: 4
20:20:35.959 [main] INFO com.insuk.reactor.ch14.internal_action.SideEffectExample -- # range > doOnNext: 5
20:20:35.960 [main] INFO com.insuk.reactor.ch14.internal_action.SideEffectExample -- # filter > doOnNext: 5
20:20:35.960 [main] INFO com.insuk.reactor.ch14.internal_action.SideEffectExample -- # hookOnNext: 5
20:20:35.960 [main] INFO com.insuk.reactor.ch14.internal_action.SideEffectExample -- # doOnRequest: 1
20:20:35.960 [main] INFO com.insuk.reactor.ch14.internal_action.SideEffectExample -- # doOnComplete()
20:20:35.960 [main] INFO com.insuk.reactor.ch14.internal_action.SideEffectExample -- # doFinally 2: onComplete
20:20:35.960 [main] INFO com.insuk.reactor.ch14.internal_action.SideEffectExample -- # doFinally 1: onComplete
 */