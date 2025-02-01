package com.insuk.reactor.ch14.transfer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/**
 * map() Operator는 Upstream에서 emit된 데이터를 mapper Function을 사용하여 변환한 후, Downstream으로 emit합니다.
 */
public class Map {
    private static final Logger log = LoggerFactory.getLogger(Map.class);

    public static void main(String[] args) {
        Flux.just("1-Circle", "2-Circle", "3-Circle")
                .map(circle -> circle.replace("Circle", "Rectangle"))
                .subscribe(data -> log.info("# onNext: {}", data));
    }
}
/*
16:35:20.816 [main] INFO com.insuk.reactor.ch14.transfer.Map -- # onNext: 1-Rectangle
16:35:20.817 [main] INFO com.insuk.reactor.ch14.transfer.Map -- # onNext: 2-Rectangle
16:35:20.817 [main] INFO com.insuk.reactor.ch14.transfer.Map -- # onNext: 3-Rectangle
 */