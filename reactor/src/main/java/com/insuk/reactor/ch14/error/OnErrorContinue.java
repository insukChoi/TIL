package com.insuk.reactor.ch14.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

/**
 * onErrorContinue() Operator는 에러가 발생했을 때, 에러 영역 내에 있는 데이터는 제거하고,
 * Upstream에서 후속 데이터를 emit하는 방식으로 에러를 복구할 수 있도록 해 줍니다.
 */
public class OnErrorContinue {
    private static final Logger log = LoggerFactory.getLogger(OnErrorContinue.class);

    public static void main(String[] args) {
        Flux.just(1, 2, 4, 0, 6, 12)
                .map(num -> 12 / num)
                .onErrorContinue((error, num) -> log.error("error: {}, num: {}", error, num))
                .subscribe(data -> log.info("# onNext: {}", data),
                        error -> log.error("# onError: ", error)
                );
    }
}
/*
21:42:33.336 [main] INFO com.insuk.reactor.ch14.error.OnErrorContinue -- # onNext: 12
21:42:33.337 [main] INFO com.insuk.reactor.ch14.error.OnErrorContinue -- # onNext: 6
21:42:33.337 [main] INFO com.insuk.reactor.ch14.error.OnErrorContinue -- # onNext: 3
21:42:33.339 [main] ERROR com.insuk.reactor.ch14.error.OnErrorContinue -- error: java.lang.ArithmeticException: / by zero, num: 0
21:42:33.339 [main] INFO com.insuk.reactor.ch14.error.OnErrorContinue -- # onNext: 2
21:42:33.339 [main] INFO com.insuk.reactor.ch14.error.OnErrorContinue -- # onNext: 1
 */
