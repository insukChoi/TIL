package com.insuk.reactor.ch14.transfer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class FlatMap_2 {
    private static final Logger log = LoggerFactory.getLogger(FlatMap_2.class);

    public static void main(String[] args) throws InterruptedException {
        Flux.range(2, 8)
                .flatMap(dan ->
                        Flux.range(1, 9)
                                .publishOn(Schedulers.parallel()) // Inner Sequence를 비동기적으로 실행하면 데이터 emit의 순서를 보장하지 않는다
                                .map(n -> dan + " * " + n + " = " + (dan * n))
                ).subscribe(log::info);

        Thread.sleep(100L);
    }
}
/*
17:14:07.917 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 6 * 1 = 6
17:14:07.918 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 2 * 1 = 2
17:14:07.918 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 2 * 2 = 4
17:14:07.918 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 2 * 3 = 6
17:14:07.918 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 2 * 4 = 8
17:14:07.918 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 2 * 5 = 10
17:14:07.918 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 2 * 6 = 12
17:14:07.918 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 2 * 7 = 14
17:14:07.918 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 2 * 8 = 16
17:14:07.918 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 2 * 9 = 18
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 3 * 1 = 3
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 3 * 2 = 6
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 3 * 3 = 9
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 3 * 4 = 12
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 3 * 5 = 15
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 3 * 6 = 18
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 3 * 7 = 21
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 3 * 8 = 24
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 3 * 9 = 27
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 4 * 1 = 4
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 4 * 2 = 8
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 4 * 3 = 12
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 4 * 4 = 16
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 4 * 5 = 20
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 4 * 6 = 24
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 4 * 7 = 28
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 4 * 8 = 32
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 4 * 9 = 36
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 5 * 1 = 5
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 5 * 2 = 10
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 5 * 3 = 15
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 5 * 4 = 20
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 5 * 5 = 25
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 5 * 6 = 30
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 5 * 7 = 35
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 5 * 8 = 40
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 5 * 9 = 45
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 7 * 1 = 7
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 7 * 2 = 14
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 7 * 3 = 21
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 7 * 4 = 28
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 7 * 5 = 35
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 7 * 6 = 42
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 7 * 7 = 49
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 7 * 8 = 56
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 7 * 9 = 63
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 8 * 1 = 8
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 8 * 2 = 16
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 8 * 3 = 24
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 8 * 4 = 32
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 8 * 5 = 40
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 8 * 6 = 48
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 8 * 7 = 56
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 8 * 8 = 64
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 8 * 9 = 72
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 9 * 1 = 9
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 9 * 2 = 18
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 9 * 3 = 27
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 9 * 4 = 36
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 9 * 5 = 45
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 9 * 6 = 54
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 9 * 7 = 63
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 9 * 8 = 72
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 9 * 9 = 81
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 6 * 2 = 12
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 6 * 3 = 18
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 6 * 4 = 24
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 6 * 5 = 30
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 6 * 6 = 36
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 6 * 7 = 42
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 6 * 8 = 48
17:14:07.919 [parallel-5] INFO com.insuk.reactor.ch14.transfer.FlatMap_2 -- 6 * 9 = 54
 */