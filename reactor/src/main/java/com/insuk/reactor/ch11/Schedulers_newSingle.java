package com.insuk.reactor.ch11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * Schedulers.single()이 하나의 스레드를 재사용하는 반면에,
 * Schedulers.newSingle()은 호출할 때마다 매번 새로운 스레드 하나를 생성합니다.
 */
public class Schedulers_newSingle {
    private static final Logger log = LoggerFactory.getLogger(Schedulers_newSingle.class);

    public static void main(String[] args) throws InterruptedException {
        doTask("task1")
                .subscribe(data -> log.info("# onNext: {}", data));

        doTask("task2")
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(200L);
    }

    private static Flux<Integer> doTask(String taskName) {
        return Flux.fromArray(new Integer[]{1, 3, 5, 7})
                .publishOn(Schedulers.newSingle("new-single", true))
                .filter(data -> data > 3)
                .doOnNext(data -> log.info("# {} doOnNext filter: {}", taskName, data))
                .map(data -> data * 10)
                .doOnNext(data -> log.info("# {} doOnNext map: {}", taskName, data));
    }
}
/*
22:11:29.575 [new-single-1] INFO com.insuk.reactor.ch11.Schedulers_newSingle -- # task1 doOnNext filter: 5
22:11:29.575 [new-single-2] INFO com.insuk.reactor.ch11.Schedulers_newSingle -- # task2 doOnNext filter: 5
22:11:29.577 [new-single-1] INFO com.insuk.reactor.ch11.Schedulers_newSingle -- # task1 doOnNext map: 50
22:11:29.577 [new-single-2] INFO com.insuk.reactor.ch11.Schedulers_newSingle -- # task2 doOnNext map: 50
22:11:29.577 [new-single-1] INFO com.insuk.reactor.ch11.Schedulers_newSingle -- # onNext: 50
22:11:29.577 [new-single-2] INFO com.insuk.reactor.ch11.Schedulers_newSingle -- # onNext: 50
22:11:29.577 [new-single-1] INFO com.insuk.reactor.ch11.Schedulers_newSingle -- # task1 doOnNext filter: 7
22:11:29.577 [new-single-2] INFO com.insuk.reactor.ch11.Schedulers_newSingle -- # task2 doOnNext filter: 7
22:11:29.577 [new-single-1] INFO com.insuk.reactor.ch11.Schedulers_newSingle -- # task1 doOnNext map: 70
22:11:29.577 [new-single-2] INFO com.insuk.reactor.ch11.Schedulers_newSingle -- # task2 doOnNext map: 70
22:11:29.577 [new-single-1] INFO com.insuk.reactor.ch11.Schedulers_newSingle -- # onNext: 70
22:11:29.577 [new-single-2] INFO com.insuk.reactor.ch11.Schedulers_newSingle -- # onNext: 70
 */