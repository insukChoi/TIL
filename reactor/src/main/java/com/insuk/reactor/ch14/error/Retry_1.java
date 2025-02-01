package com.insuk.reactor.ch14.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * retry() Operator는 Publisher가 데이터를 emit하는 과정에서 에러가 발생하면 파라미터로 입력한 횟수만큼
 * 원본 Flux의 Sequence를 다시 구독합니다.
 */
public class Retry_1 {
    private static final Logger log = LoggerFactory.getLogger(Retry_1.class);

    public static void main(String[] args) throws InterruptedException {
        final int[] count = {1};
        Flux.range(1, 3)
                .delayElements(Duration.ofSeconds(1))
                .map(num -> {
                    try {
                        if (num == 3 && count[0] == 1) {
                            count[0]++;
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {}

                    return num;
                })
                .timeout(Duration.ofMillis(1500))
                .retry(1)
                .subscribe(data -> log.info("# onNext: {}", data),
                        error -> log.error("# onError: ", error),
                        () -> log.info("# onComplete")
                );

        Thread.sleep(7000);
    }
}
/*
21:52:37.701 [parallel-1] INFO com.insuk.reactor.ch14.error.Retry_1 -- # onNext: 1
21:52:38.711 [parallel-4] INFO com.insuk.reactor.ch14.error.Retry_1 -- # onNext: 2
21:52:41.223 [parallel-7] INFO com.insuk.reactor.ch14.error.Retry_1 -- # onNext: 1
21:52:42.228 [parallel-10] INFO com.insuk.reactor.ch14.error.Retry_1 -- # onNext: 2
21:52:43.231 [parallel-2] INFO com.insuk.reactor.ch14.error.Retry_1 -- # onNext: 3
21:52:43.232 [parallel-2] INFO com.insuk.reactor.ch14.error.Retry_1 -- # onComplete
 */