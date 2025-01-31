package com.insuk.reactor.ch14.filtering;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * take() Operator는 Upstream에서 emit되는 데이터 중에서 파라미터로 입력받은 숫자만큼만 Downstream으로 emit합니다.
 */
public class Take {
    private static final Logger log = LoggerFactory.getLogger(Take.class);

    public static void main(String[] args) throws InterruptedException {
        Flux.interval(Duration.ofSeconds(1))
                .take(3)
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(5000L);
    }
}
/*
21:39:04.542 [parallel-1] INFO com.insuk.reactor.ch14.filtering.Take -- # onNext: 0
21:39:05.533 [parallel-1] INFO com.insuk.reactor.ch14.filtering.Take -- # onNext: 1
21:39:06.536 [parallel-1] INFO com.insuk.reactor.ch14.filtering.Take -- # onNext: 2
 */