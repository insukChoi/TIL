package com.insuk.reactor.ch14.division;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/**
 * buffer(int maxSize) Operator는 Upstream에서 emit되는 첫 번째 데이터부터
 * maxSize 숫자만큼의 데이터를 List 버퍼로 한번에 emit 합니다.
 */
public class Buffer {
    private static final Logger log = LoggerFactory.getLogger(Buffer.class);

    public static void main(String[] args) {
        Flux.range(1, 95)
                .buffer(10)
                .subscribe(buffer -> log.info("# onNext: {}", buffer));
    }
}
/*
13:25:46.281 [main] INFO com.insuk.reactor.ch14.division.Buffer -- # onNext: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
13:25:46.283 [main] INFO com.insuk.reactor.ch14.division.Buffer -- # onNext: [11, 12, 13, 14, 15, 16, 17, 18, 19, 20]
13:25:46.283 [main] INFO com.insuk.reactor.ch14.division.Buffer -- # onNext: [21, 22, 23, 24, 25, 26, 27, 28, 29, 30]
13:25:46.283 [main] INFO com.insuk.reactor.ch14.division.Buffer -- # onNext: [31, 32, 33, 34, 35, 36, 37, 38, 39, 40]
13:25:46.283 [main] INFO com.insuk.reactor.ch14.division.Buffer -- # onNext: [41, 42, 43, 44, 45, 46, 47, 48, 49, 50]
13:25:46.283 [main] INFO com.insuk.reactor.ch14.division.Buffer -- # onNext: [51, 52, 53, 54, 55, 56, 57, 58, 59, 60]
13:25:46.283 [main] INFO com.insuk.reactor.ch14.division.Buffer -- # onNext: [61, 62, 63, 64, 65, 66, 67, 68, 69, 70]
13:25:46.283 [main] INFO com.insuk.reactor.ch14.division.Buffer -- # onNext: [71, 72, 73, 74, 75, 76, 77, 78, 79, 80]
13:25:46.283 [main] INFO com.insuk.reactor.ch14.division.Buffer -- # onNext: [81, 82, 83, 84, 85, 86, 87, 88, 89, 90]
13:25:46.283 [main] INFO com.insuk.reactor.ch14.division.Buffer -- # onNext: [91, 92, 93, 94, 95]
 */