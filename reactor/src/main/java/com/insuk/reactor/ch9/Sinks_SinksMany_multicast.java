package com.insuk.reactor.ch9;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Sinks_SinksMany_multicast {
    private static final Logger log = LoggerFactory.getLogger(Sinks_SinksMany_multicast.class);

    public static void main(String[] args) {
        Sinks.Many<Integer> multicastSink = Sinks.many().multicast().onBackpressureBuffer();
        Flux<Integer> fluxView = multicastSink.asFlux();

        multicastSink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        multicastSink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);

        fluxView.subscribe(data -> log.info("# Subscriber1: {}", data));
        fluxView.subscribe(data -> log.info("# Subscriber2: {}", data));

        multicastSink.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);
    }
}
/*
15:16:23.114 [main] INFO com.insuk.reactor.ch9.Sinks_SinksMany_multicast -- # Subscriber1: 1
15:16:23.116 [main] INFO com.insuk.reactor.ch9.Sinks_SinksMany_multicast -- # Subscriber1: 2
15:16:23.116 [main] INFO com.insuk.reactor.ch9.Sinks_SinksMany_multicast -- # Subscriber1: 3
15:16:23.116 [main] INFO com.insuk.reactor.ch9.Sinks_SinksMany_multicast -- # Subscriber2: 3
 */
/**
 * Subscriber1 은 emit 된 세 개의 데이터 모두를 전달받은 반면에, Subscriber2 는 세 번째 데이터만 전달받았습니다.
 * Sinks 가 Publisher의 역할을 할 경우 기본적으로 Hot Publisher 로 동작하며, 특히 onBackpressureBuffer() 메서드는 Warm up의 특징을 가지는
 * Hot Sequence로 동작하기 때문에 첫 번째 구독이 발생한 시점에 Downstream 쪽으로 데이터가 전달되는 것입니다.
 */