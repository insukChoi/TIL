package com.insuk.reactor.ch14;

import com.insuk.reactor.sample.SampleData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/**
 * range(n, m)
 * range() Operator는 n부터 1씩 증가한 연속된 수를 m개 emit하는 Flux를 생성합니다.
 */
public class Range {
    private static final Logger log = LoggerFactory.getLogger(Range.class);

    public static void main(String[] args) {
        Flux.range(5, 10)
                .subscribe(data -> log.info("{}", data));

        Flux.range(7, 5)
                .map(idx -> SampleData.btcTopPricesPerYear.get(idx))
                .subscribe(tuple -> log.info("{}'s {}", tuple.getT1(), tuple.getT2()));
    }
}
/*
15:20:39.684 [main] INFO com.insuk.reactor.ch14.Range -- 5
15:20:39.685 [main] INFO com.insuk.reactor.ch14.Range -- 6
15:20:39.685 [main] INFO com.insuk.reactor.ch14.Range -- 7
15:20:39.686 [main] INFO com.insuk.reactor.ch14.Range -- 8
15:20:39.686 [main] INFO com.insuk.reactor.ch14.Range -- 9
15:20:39.686 [main] INFO com.insuk.reactor.ch14.Range -- 10
15:20:39.686 [main] INFO com.insuk.reactor.ch14.Range -- 11
15:20:39.686 [main] INFO com.insuk.reactor.ch14.Range -- 12
15:20:39.686 [main] INFO com.insuk.reactor.ch14.Range -- 13
15:20:39.686 [main] INFO com.insuk.reactor.ch14.Range -- 14
 */
/*
15:27:54.056 [main] INFO com.insuk.reactor.ch14.Range -- 2017's 22483583
15:27:54.056 [main] INFO com.insuk.reactor.ch14.Range -- 2018's 19521543
15:27:54.056 [main] INFO com.insuk.reactor.ch14.Range -- 2019's 15761568
15:27:54.056 [main] INFO com.insuk.reactor.ch14.Range -- 2020's 22439002
15:27:54.056 [main] INFO com.insuk.reactor.ch14.Range -- 2021's 63364000
 */