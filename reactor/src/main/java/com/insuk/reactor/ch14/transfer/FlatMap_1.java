package com.insuk.reactor.ch14.transfer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/**
 * FlatMap_1() Operator 는 Upstream에서 emit된 데이터 한 건이 Inner Sequence에서 여러 건의 데이터로 변환된다
 * Upstream에서 emit된 데이터는 이렇게 Inner Sequence에서 평탄화(Flatten) 작업을 거치면서 하나의 Sequence로 병합(merge)되어 Downstream으로 emit된다.
 */
public class FlatMap_1 {
    private static final Logger log = LoggerFactory.getLogger(FlatMap_1.class);

    public static void main(String[] args) {
        Flux.just("Good", "Bad")
                .flatMap(feeling ->
                        Flux.just("Morning", "Afternoon", "Evening")
                                .map(time -> feeling + " " + time)
                ).subscribe(log::info);
    }
}
