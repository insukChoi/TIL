package com.insuk.reactor.ch9;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * 네트워크 통신에서 사용하는 Brodcast 라는 용어는 네트워크에 연결된 모든 시스템이 정보를 전달받는(One to ALL)방식입니다,
 * 반면에 Unicast 는 하나의 특정 시스템만 정보를 전달받는(One to One)방식이고,
 * Multicast 는 일부 시스템들만 정보를 전달받는(One to Many)방식입니다,
 * 따라서 unicast의 의미를 가지는 UnicastSpec의 기능은 단 하나의 Subscriber에게만 데이터를 emit 하는 것입니다,
 */
public class Sinks_SinksMany_unicast {
    private static final Logger log = LoggerFactory.getLogger(Sinks_SinksMany_unicast.class);

    public static void main(String[] args) {
        Sinks.Many<Integer> unicastSinks = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Integer> fluxView = unicastSinks.asFlux();

        unicastSinks.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        unicastSinks.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);

        fluxView.subscribe(data -> log.info("# Subscriber1: {}", data));

        unicastSinks.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);

//        fluxView.subscribe(data -> log.info("# Subscriber2: {}", data));
    }
}
/*
15:08:48.692 [main] INFO com.insuk.reactor.ch9.Sinks_SinksMany_unicast -- # Subscriber1: 1
15:08:48.694 [main] INFO com.insuk.reactor.ch9.Sinks_SinksMany_unicast -- # Subscriber1: 2
15:08:48.694 [main] INFO com.insuk.reactor.ch9.Sinks_SinksMany_unicast -- # Subscriber1: 3
 */
/**
 * 주석을 해제하고 실행하면,
 * Caused by: java.lang.IllegalStateException: Sinks.many().unicast() sinks only allow a single Subscriber
 * 라는 오류가 발생한다.
 * 단 하나의 Subscriber 에게 데이터를 emit 하기 위해서 내부적으로 UnicastProcessor를 사용한다는 것을 알 수 있다.
 */