package com.insuk.reactor.ch14.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * error() Operator는 파라미터로 지정된 에러로 종료하는 Flux를 생성합니다.
 * <p>
 * error() Operator는 마치 Java의 throw 키워드를 사용해서 예외를 의도적으로 던지는 것 같은 역할을 하는데 주로 체크 예외(Checked Exception)를
 * 캐치해서 다시 던져야 하는 경우 사용할 수 있습니다.
 */
public class Error_1 {
    private static final Logger log = LoggerFactory.getLogger(Error_1.class);

    public static void main(String[] args) {
        Flux.range(1, 5)
                .flatMap(num -> {
                    if ((num * 2) % 3 == 0) {
                        return Flux.error(
                                new IllegalArgumentException("Not allowed multiple of 3")
                        );
                    } else {
                        return Mono.just(num * 2);
                    }
                }).subscribe(
                        data -> log.info("# onNext: {}", data),
                        error -> log.error("# onError: ", error)
                );
    }
}
/*
20:58:47.437 [main] INFO com.insuk.reactor.ch14.error.Error_1 -- # onNext: 2
20:58:47.439 [main] INFO com.insuk.reactor.ch14.error.Error_1 -- # onNext: 4
20:58:47.441 [main] ERROR com.insuk.reactor.ch14.error.Error_1 -- # onError:
java.lang.IllegalArgumentException: Not allowed multiple of 3
	at com.insuk.reactor.ch14.error.Error_1.lambda$main$0(Error_1.java:21)
	at reactor.core.publisher.FluxFlatMap$FlatMapMain.onNext(FluxFlatMap.java:388)
	at reactor.core.publisher.FluxRange$RangeSubscription.slowPath(FluxRange.java:156)
	at reactor.core.publisher.FluxRange$RangeSubscription.request(FluxRange.java:111)
	at reactor.core.publisher.FluxFlatMap$FlatMapMain.onSubscribe(FluxFlatMap.java:373)
	at reactor.core.publisher.FluxRange.subscribe(FluxRange.java:69)
	at reactor.core.publisher.Flux.subscribe(Flux.java:8891)
	at reactor.core.publisher.Flux.subscribeWith(Flux.java:9012)
	at reactor.core.publisher.Flux.subscribe(Flux.java:8856)
	at reactor.core.publisher.Flux.subscribe(Flux.java:8780)
	at reactor.core.publisher.Flux.subscribe(Flux.java:8750)
	at com.insuk.reactor.ch14.error.Error_1.main(Error_1.java:27)
 */