package com.insuk.reactor.ch10;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * subscribeOn() Operator와 publishOn() Operator의 경우, 동시성을 가지는 논리적인 스레드에 해당되지만,
 * parallel() Operator는 병렬성을 가지는 물리적인 스레드에 해당합니다.
 *
 * parallel()의 경우, '라운드 로빈' 방식으로 CPU 코어 개수만큼의 스레드를 병렬로 실행합니다.
 */
public class Parallel {
    private static final Logger log = LoggerFactory.getLogger(Parallel.class);

    public static void main(String[] args) throws InterruptedException {
        Flux.fromArray(new Integer[]{1, 3, 5, 7, 9, 11, 13, 15, 17, 19})
                .parallel()
                .runOn(Schedulers.parallel())
                .subscribe(data -> log.info("# onNext {}", data));

        Thread.sleep(100L);
    }
}
/*
22:46:26.159 [parallel-3] INFO com.insuk.reactor.ch10.Parallel -- # onNext 5
22:46:26.159 [parallel-1] INFO com.insuk.reactor.ch10.Parallel -- # onNext 1
22:46:26.159 [parallel-10] INFO com.insuk.reactor.ch10.Parallel -- # onNext 19
22:46:26.159 [parallel-7] INFO com.insuk.reactor.ch10.Parallel -- # onNext 13
22:46:26.159 [parallel-4] INFO com.insuk.reactor.ch10.Parallel -- # onNext 7
22:46:26.159 [parallel-2] INFO com.insuk.reactor.ch10.Parallel -- # onNext 3
22:46:26.159 [parallel-8] INFO com.insuk.reactor.ch10.Parallel -- # onNext 15
22:46:26.159 [parallel-5] INFO com.insuk.reactor.ch10.Parallel -- # onNext 9
22:46:26.159 [parallel-6] INFO com.insuk.reactor.ch10.Parallel -- # onNext 11
22:46:26.159 [parallel-9] INFO com.insuk.reactor.ch10.Parallel -- # onNext 17
*/
/**
 * parallel() Operator만 추가한다고 해서 emit되는 데이터를 병렬로 처리하지 않습니다.
 * parallel() Operator는 emit 되는 데이터를 CPU의 논리적인 코어(물리적인 스레드) 수에 맞게 사전에 골고루 분배하는 역할만 하며,
 * 실제로 병렬 작업을 수행할 스레드의 할당은 runOn() Operator가 담당합니다.
 */