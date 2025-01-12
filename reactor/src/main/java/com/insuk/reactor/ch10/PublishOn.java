package com.insuk.reactor.ch10;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

// publishOn() Operator는 코드상에서 publishOn()을 기준으로 아래쪽인 Downstream의 실행 스레드를 변경합니다.
public class PublishOn {
    private static final Logger log = LoggerFactory.getLogger(PublishOn.class);

    public static void main(String[] args) throws InterruptedException {
        Flux.fromArray(new Integer[]{1, 3, 5, 7})
                .doOnNext(data -> log.info("# doOnNext: {}", data))
                .doOnSubscribe(disposable -> log.info("# doOnSubscribe: {}", disposable))
                .publishOn(Schedulers.parallel())
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(500L);
    }
}
/*
22:27:15.958 [main] INFO com.insuk.reactor.ch10.PublishOn -- # doOnSubscribe: reactor.core.publisher.FluxPeekFuseable$PeekFuseableSubscriber@c46bcd4
22:27:15.961 [main] INFO com.insuk.reactor.ch10.PublishOn -- # doOnNext: 1
22:27:15.961 [main] INFO com.insuk.reactor.ch10.PublishOn -- # doOnNext: 3
22:27:15.961 [main] INFO com.insuk.reactor.ch10.PublishOn -- # doOnNext: 5
22:27:15.961 [main] INFO com.insuk.reactor.ch10.PublishOn -- # doOnNext: 7
22:27:15.961 [parallel-1] INFO com.insuk.reactor.ch10.PublishOn -- # onNext: 1
22:27:15.961 [parallel-1] INFO com.insuk.reactor.ch10.PublishOn -- # onNext: 3
22:27:15.961 [parallel-1] INFO com.insuk.reactor.ch10.PublishOn -- # onNext: 5
22:27:15.961 [parallel-1] INFO com.insuk.reactor.ch10.PublishOn -- # onNext: 7
 */
