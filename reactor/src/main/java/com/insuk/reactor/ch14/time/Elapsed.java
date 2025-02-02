package com.insuk.reactor.ch14.time;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * elapsed() Operator는 emit된 데이터 사이의 경과 시간을 측정해서 Tuple<Long, T> 형태로 Downstream에 emit합니다.
 */
public class Elapsed {
    private static final Logger log = LoggerFactory.getLogger(Elapsed.class);

    public static void main(String[] args) throws InterruptedException {
        Flux.range(1, 5)
                .delayElements(Duration.ofSeconds(1))
                .elapsed()
                .subscribe(data -> log.info("# onNext: {}, time: {}", data.getT2(), data.getT1()));

        Thread.sleep(6000);
    }
}
/*
13:12:50.688 [parallel-1] INFO com.insuk.reactor.ch14.time.Elapsed -- # onNext: 1, time: 1017
13:12:51.696 [parallel-2] INFO com.insuk.reactor.ch14.time.Elapsed -- # onNext: 2, time: 1008
13:12:52.697 [parallel-3] INFO com.insuk.reactor.ch14.time.Elapsed -- # onNext: 3, time: 1001
13:12:53.702 [parallel-4] INFO com.insuk.reactor.ch14.time.Elapsed -- # onNext: 4, time: 1005
13:12:54.708 [parallel-5] INFO com.insuk.reactor.ch14.time.Elapsed -- # onNext: 5, time: 1006
 */
