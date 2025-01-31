package com.insuk.reactor.ch14.filtering;

import com.insuk.reactor.sample.SampleData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/**
 * next() Operator는 Upstream에서 emit되는 데이터 중에서 첫 번째 데이터만 Downstream으로 emit합니다.
 * 만일 Upstream에서 emit되는 데이터가 empty라면 Downsteam으로 empty Mono를 emit합니다.
 */
public class Next {
    private static final Logger log = LoggerFactory.getLogger(Next.class);

    public static void main(String[] args) {
        Flux.fromIterable(SampleData.btcTopPricesPerYear)
                .next()
                .subscribe(tuple -> log.info("# onNext: {}, {}", tuple.getT1(), tuple.getT2()));
    }
}
/*
21:43:50.146 [main] INFO com.insuk.reactor.ch14.filtering.Next -- # onNext: 2010, 565
 */
