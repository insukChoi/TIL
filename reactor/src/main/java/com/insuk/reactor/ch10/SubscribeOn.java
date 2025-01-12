package com.insuk.reactor.ch10;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

// subscribeOn() Operator 는 그 이름처럼 구독이 발생한 직후 실행될 스레드를 지정하는 Operator 입니다.
public class SubscribeOn {
    private static final Logger log = LoggerFactory.getLogger(SubscribeOn.class);

    public static void main(String[] args) throws InterruptedException {
        Flux.fromArray(new Integer[] {1, 3, 5, 7})
                .subscribeOn(Schedulers.boundedElastic())
                .doOnNext(data -> log.info("# doOnNext: {}", data))
                .doOnSubscribe(subscription -> log.info("# doOnSubscribe: {}", subscription))
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(500L);
    }
}
/*
22:02:22.109 [parallel-1] INFO com.insuk.reactor.ch10.SubscribeOn -- # doOnSubscribe: reactor.core.publisher.FluxPeek$PeekSubscriber@35097502
22:02:22.111 [boundedElastic-1] INFO com.insuk.reactor.ch10.SubscribeOn -- # doOnNext: 1
22:02:22.112 [boundedElastic-1] INFO com.insuk.reactor.ch10.SubscribeOn -- # onNext: 1
22:02:22.112 [boundedElastic-1] INFO com.insuk.reactor.ch10.SubscribeOn -- # doOnNext: 3
22:02:22.112 [boundedElastic-1] INFO com.insuk.reactor.ch10.SubscribeOn -- # onNext: 3
22:02:22.112 [boundedElastic-1] INFO com.insuk.reactor.ch10.SubscribeOn -- # doOnNext: 5
22:02:22.112 [boundedElastic-1] INFO com.insuk.reactor.ch10.SubscribeOn -- # onNext: 5
22:02:22.112 [boundedElastic-1] INFO com.insuk.reactor.ch10.SubscribeOn -- # doOnNext: 7
22:02:22.112 [boundedElastic-1] INFO com.insuk.reactor.ch10.SubscribeOn -- # onNext: 7
 */
/**
 * 실행 결과를 보면 doOnSubscribe() 에서의 동작은 main 스레드에서 실행되는데, 그 이유는 예제 코드의 최초 실행 스레드가 main 스레드이기 때문입니다.
 * subscribeOn() 을 추가하지 않았다면 원본 Flux의 처리 동작은 여전히 main 스레드에서 실행되겠지만, subscribeOn()에서 Scheduler를 지정했기 때문에
 * 구독이 발생한 직후부터는 원본 Flux의 동작을 처리하는 스레드가 바뀌게 됩니다.
 */