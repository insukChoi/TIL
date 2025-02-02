package com.insuk.reactor.ch14.division;

import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

/**
 * window(int maxSize) Operator는 Upstream에서 emit되는 첫 번째 데이터부터
 * maxSize 숫자만큼의 데이터를 포함하는 새로운 Flux로 분할합니다.
 */
public class Window {
    private static final Logger log = LoggerFactory.getLogger(Window.class);

    public static void main(String[] args) {
        Flux.range(1, 11)
                .window(3)
                .flatMap(flux -> {
                    log.info("====================");
                    return flux;
                })
                .subscribe(new BaseSubscriber<>() {
                    @Override
                    protected void hookOnSubscribe(final Subscription subscription) {
                        subscription.request(2);
                    }

                    @Override
                    protected void hookOnNext(final Integer value) {
                        log.info("# onNext: {}", value);
                        request(2);
                    }
                });
    }
}
/*
13:23:21.507 [main] INFO com.insuk.reactor.ch14.division.Window -- ====================
13:23:21.509 [main] INFO com.insuk.reactor.ch14.division.Window -- # onNext: 1
13:23:21.510 [main] INFO com.insuk.reactor.ch14.division.Window -- # onNext: 2
13:23:21.510 [main] INFO com.insuk.reactor.ch14.division.Window -- # onNext: 3
13:23:21.510 [main] INFO com.insuk.reactor.ch14.division.Window -- ====================
13:23:21.510 [main] INFO com.insuk.reactor.ch14.division.Window -- # onNext: 4
13:23:21.510 [main] INFO com.insuk.reactor.ch14.division.Window -- # onNext: 5
13:23:21.510 [main] INFO com.insuk.reactor.ch14.division.Window -- # onNext: 6
13:23:21.510 [main] INFO com.insuk.reactor.ch14.division.Window -- ====================
13:23:21.510 [main] INFO com.insuk.reactor.ch14.division.Window -- # onNext: 7
13:23:21.510 [main] INFO com.insuk.reactor.ch14.division.Window -- # onNext: 8
13:23:21.510 [main] INFO com.insuk.reactor.ch14.division.Window -- # onNext: 9
13:23:21.510 [main] INFO com.insuk.reactor.ch14.division.Window -- ====================
13:23:21.510 [main] INFO com.insuk.reactor.ch14.division.Window -- # onNext: 10
13:23:21.510 [main] INFO com.insuk.reactor.ch14.division.Window -- # onNext: 11
 */