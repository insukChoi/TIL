package com.insuk.reactor.ch14.filtering;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * skip() Operator는 Upstream에서 emit된 데이터 중에서 파라미터로 입력받은 숫자만큼 건너띈 후,
 * 나머지 데이터를 Downstream으로 emit합니다.
 */
public class Skip {
    private static final Logger log = LoggerFactory.getLogger(Skip.class);

    public static void main(String[] args) throws InterruptedException {
        Flux.interval(Duration.ofSeconds(1))
                .skip(2)
                .subscribe(data -> log.info("# onNext: {}", data));

        Thread.sleep(5500L);
    }
}
/* 0과 1을 건너띄고, 2,3,4 가 나옴
21:35:10.591 [parallel-1] INFO com.insuk.reactor.ch14.filtering.Skip -- # onNext: 2
21:35:11.594 [parallel-1] INFO com.insuk.reactor.ch14.filtering.Skip -- # onNext: 3
21:35:12.590 [parallel-1] INFO com.insuk.reactor.ch14.filtering.Skip -- # onNext: 4
 */