package com.insuk.reactor.ch14.division;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * bufferTimeout(maxSize, maxTime) Operator는 Upstream에서 emit되는 첫번째 데이터부터
 * maxSize 숫자만큼의 데이터 또는 maxTime 내에 emit된 데이터를 List 버퍼로 한 번에 emit 합니다.
 */
public class BufferTimeout {
    private static final Logger log = LoggerFactory.getLogger(BufferTimeout.class);

    public static void main(String[] args) {
        Flux.range(1, 20)
                .map(num -> {
                    try {
                        if (num < 10) {
                            Thread.sleep(100L);
                        } else {
                            Thread.sleep(300L);
                        }
                    } catch (InterruptedException e) {}
                    return num;
                })
                .bufferTimeout(3, Duration.ofMillis(400L))
                .subscribe(buffer -> log.info("# onNext: {}", buffer));
    }
}
/*
13:35:25.671 [main] INFO com.insuk.reactor.ch14.division.BufferTimeout -- # onNext: [1, 2, 3]
13:35:25.990 [main] INFO com.insuk.reactor.ch14.division.BufferTimeout -- # onNext: [4, 5, 6]
13:35:26.300 [main] INFO com.insuk.reactor.ch14.division.BufferTimeout -- # onNext: [7, 8, 9]
13:35:27.010 [parallel-1] INFO com.insuk.reactor.ch14.division.BufferTimeout -- # onNext: [10, 11]
13:35:27.613 [parallel-1] INFO com.insuk.reactor.ch14.division.BufferTimeout -- # onNext: [12, 13]
13:35:28.224 [parallel-1] INFO com.insuk.reactor.ch14.division.BufferTimeout -- # onNext: [14, 15]
13:35:28.834 [parallel-1] INFO com.insuk.reactor.ch14.division.BufferTimeout -- # onNext: [16, 17]
13:35:29.441 [parallel-1] INFO com.insuk.reactor.ch14.division.BufferTimeout -- # onNext: [18, 19]
13:35:29.653 [main] INFO com.insuk.reactor.ch14.division.BufferTimeout -- # onNext: [20]
 */