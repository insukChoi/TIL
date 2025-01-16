package com.insuk.reactor.ch11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * Schedulers,immediate()은 별도의 스레드를 추가적으로 생성하지 않고, 현재 스레드에서 작업을 처리하고자 할 때 사용한다.
 */
public class Schedulers_immediate {
    private static final Logger log = LoggerFactory.getLogger(Schedulers_immediate.class);

    public static void main(String[] args) throws InterruptedException {
        Flux.fromArray(new Integer[]{1, 3, 5, 7})
                .publishOn(Schedulers.parallel())
                .filter(data -> data > 3)
                .doOnNext(data -> log.info("# doOnNext filter: {}", data))
                .publishOn(Schedulers.immediate())
                .map(data -> data * 10)
                .doOnNext(data -> log.info("# doOnNext map: {}", data))
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(200L);
    }
}
/*
21:55:37.042 [parallel-1] INFO com.insuk.reactor.ch11.Schedulers_immediate -- # doOnNext filter: 5
21:55:37.043 [parallel-1] INFO com.insuk.reactor.ch11.Schedulers_immediate -- # doOnNext map: 50
21:55:37.043 [parallel-1] INFO com.insuk.reactor.ch11.Schedulers_immediate -- # onNext: 50
21:55:37.044 [parallel-1] INFO com.insuk.reactor.ch11.Schedulers_immediate -- # doOnNext filter: 7
21:55:37.044 [parallel-1] INFO com.insuk.reactor.ch11.Schedulers_immediate -- # doOnNext map: 70
21:55:37.044 [parallel-1] INFO com.insuk.reactor.ch11.Schedulers_immediate -- # onNext: 70
 */