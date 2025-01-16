package com.insuk.reactor.ch11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * Schedulers.single()은 스레드 하나만 생성해서 Scheduler가 제거되기 전까지 재사용하는 방식이다.
 */
public class Schedulers_single {
    private static final Logger log = LoggerFactory.getLogger(Schedulers_single.class);

    public static void main(String[] args) throws InterruptedException {
        doTask("task1")
                .subscribe(data -> log.info("# onNext: {}", data));

        doTask("task2")
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(200L);
    }

    private static Flux<Integer> doTask(String taskName) {
        return Flux.fromArray(new Integer[]{1, 3, 5, 7})
                .publishOn(Schedulers.single())
                .filter(data -> data > 3)
                .doOnNext(data -> log.info("# {} doOnNext filter: {}", taskName, data))
                .map(data -> data * 10)
                .doOnNext(data -> log.info("# {} doOnNext map: {}", taskName, data));
    }
}
/*
22:04:48.173 [single-1] INFO com.insuk.reactor.ch11.Schedulers_single -- # task1 doOnNext filter: 5
22:04:48.174 [single-1] INFO com.insuk.reactor.ch11.Schedulers_single -- # task1 doOnNext map: 50
22:04:48.174 [single-1] INFO com.insuk.reactor.ch11.Schedulers_single -- # onNext: 50
22:04:48.174 [single-1] INFO com.insuk.reactor.ch11.Schedulers_single -- # task1 doOnNext filter: 7
22:04:48.174 [single-1] INFO com.insuk.reactor.ch11.Schedulers_single -- # task1 doOnNext map: 70
22:04:48.174 [single-1] INFO com.insuk.reactor.ch11.Schedulers_single -- # onNext: 70
22:04:48.175 [single-1] INFO com.insuk.reactor.ch11.Schedulers_single -- # task2 doOnNext filter: 5
22:04:48.175 [single-1] INFO com.insuk.reactor.ch11.Schedulers_single -- # task2 doOnNext map: 50
22:04:48.175 [single-1] INFO com.insuk.reactor.ch11.Schedulers_single -- # onNext: 50
22:04:48.175 [single-1] INFO com.insuk.reactor.ch11.Schedulers_single -- # task2 doOnNext filter: 7
22:04:48.175 [single-1] INFO com.insuk.reactor.ch11.Schedulers_single -- # task2 doOnNext map: 70
22:04:48.175 [single-1] INFO com.insuk.reactor.ch11.Schedulers_single -- # onNext: 70
 */
