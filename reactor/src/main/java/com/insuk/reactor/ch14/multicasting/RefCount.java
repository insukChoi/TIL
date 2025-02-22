package com.insuk.reactor.ch14.multicasting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * refCount() Operator는 파라미터로 입력된 숫자만큼의 구독이 발생하는 시점에 Upstream 소스에 연결되며,
 * 모든 구독이 취소되거나 Upstream의 데이터 emit이 종료되면 연결이 해제됩니다.
 *
 * refCount() Operator는 주로 무한 스트림 상황에서 모든 구독이 취소될 경우 연결을 해제하는 데 사용할 수 있습니다.
 */
public class RefCount {
    private static final Logger log = LoggerFactory.getLogger(RefCount.class);

    public static void main(String[] args) throws InterruptedException {
        Flux<Long> publisher = Flux.interval(Duration.ofMillis(500))
//                .publish().autoConnect(1);
                .publish().refCount(1);

        Disposable disposable = publisher.subscribe(data -> log.info("# subsciber 1: {}", data));

        Thread.sleep(2100L);
        disposable.dispose();

        publisher.subscribe(data -> log.info("# subsciber 2: {}", data));

        Thread.sleep(2500L);
    }
}
/*
14:36:36.064 [parallel-1] INFO com.insuk.reactor.ch14.multicasting.RefCount -- # subsciber 1: 0
14:36:36.563 [parallel-1] INFO com.insuk.reactor.ch14.multicasting.RefCount -- # subsciber 1: 1
14:36:37.062 [parallel-1] INFO com.insuk.reactor.ch14.multicasting.RefCount -- # subsciber 1: 2
14:36:37.563 [parallel-1] INFO com.insuk.reactor.ch14.multicasting.RefCount -- # subsciber 1: 3
14:36:38.171 [parallel-2] INFO com.insuk.reactor.ch14.multicasting.RefCount -- # subsciber 2: 0
14:36:38.670 [parallel-2] INFO com.insuk.reactor.ch14.multicasting.RefCount -- # subsciber 2: 1
14:36:39.168 [parallel-2] INFO com.insuk.reactor.ch14.multicasting.RefCount -- # subsciber 2: 2
14:36:39.671 [parallel-2] INFO com.insuk.reactor.ch14.multicasting.RefCount -- # subsciber 2: 3
14:36:40.168 [parallel-2] INFO com.insuk.reactor.ch14.multicasting.RefCount -- # subsciber 2: 4
 */