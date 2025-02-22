package com.insuk.reactor.ch14.multicasting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * publish() Operator는 구독을 하더라도 구독 시점에 즉시 데이터를 emit하지 않고,
 * connect()를 호출하는 시점에 비로소 데이터를 emit합니다.
 * 그리고 Hot Sequence로 변환되기 때문에 구독 시점 이후에 emit된 데이터만 전달받을 수 있습니다.
 */
public class Publish_1 {
    private static final Logger log = LoggerFactory.getLogger(Publish_1.class);

    public static void main(String[] args) throws InterruptedException {
        ConnectableFlux<Integer> flux =
                Flux.range(1, 5)
                        .delayElements(Duration.ofMillis(300L))
                        .publish();

        Thread.sleep(500L);
        flux.subscribe(data -> log.info("# subscriber1: {}", data));

        Thread.sleep(200L);
        flux.subscribe(data -> log.info("# subscriber2: {}", data));

        flux.connect(); // connect() 되면, Flux가 300L 에 한번씩 emit된다.

        Thread.sleep(1000L);
        flux.subscribe(data -> log.info("# subscriber3: {}", data));

        Thread.sleep(2000L);
    }
}
/*
13:39:48.659 [parallel-1] INFO com.insuk.reactor.ch14.multicasting.Publish_1 -- # subscriber1: 1
13:39:48.663 [parallel-1] INFO com.insuk.reactor.ch14.multicasting.Publish_1 -- # subscriber2: 1
13:39:48.967 [parallel-2] INFO com.insuk.reactor.ch14.multicasting.Publish_1 -- # subscriber1: 2
13:39:48.967 [parallel-2] INFO com.insuk.reactor.ch14.multicasting.Publish_1 -- # subscriber2: 2
13:39:49.272 [parallel-3] INFO com.insuk.reactor.ch14.multicasting.Publish_1 -- # subscriber1: 3
13:39:49.272 [parallel-3] INFO com.insuk.reactor.ch14.multicasting.Publish_1 -- # subscriber2: 3
13:39:49.578 [parallel-4] INFO com.insuk.reactor.ch14.multicasting.Publish_1 -- # subscriber1: 4
13:39:49.578 [parallel-4] INFO com.insuk.reactor.ch14.multicasting.Publish_1 -- # subscriber2: 4
13:39:49.578 [parallel-4] INFO com.insuk.reactor.ch14.multicasting.Publish_1 -- # subscriber3: 4
13:39:49.884 [parallel-5] INFO com.insuk.reactor.ch14.multicasting.Publish_1 -- # subscriber1: 5
13:39:49.885 [parallel-5] INFO com.insuk.reactor.ch14.multicasting.Publish_1 -- # subscriber2: 5
13:39:49.885 [parallel-5] INFO com.insuk.reactor.ch14.multicasting.Publish_1 -- # subscriber3: 5
 */